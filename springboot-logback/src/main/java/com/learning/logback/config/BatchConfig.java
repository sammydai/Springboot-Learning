package com.learning.logback.config;

import com.learning.logback.tasklet.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import javax.annotation.Resource;

/**
 * 核心配置：拆分Flow构建Job，避免FlowJobBuilder报错，包含重试、自定义状态
 */
@Configuration
public class BatchConfig {

    // 注入SpringBatch核心工厂（@EnableBatchProcessing自动创建）
    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepBuilderFactory stepBuilderFactory;

    // ====================== 1. 定义Tasklet Bean ======================
    @Bean
    public Tasklet initOrderTasklet() {
        return new InitOrderTasklet();
    }

    @Bean
    public Tasklet processOrderTasklet() {
        return new ProcessOrderTasklet();
    }

    @Bean
    public Tasklet errorHandleTasklet() {
        return new ErrorHandleTasklet();
    }

    @Bean
    public Tasklet cleanResourceTasklet() {
        return new CleanResourceTasklet();
    }

    // ====================== 2. 定义Step（包含重试规则） ======================
    @Bean
    public Step step1InitOrder() {
        return stepBuilderFactory.get("step1InitOrder")
                .tasklet(initOrderTasklet())
                .build();
    }

    // 替换原有step2ProcessOrder的Bean定义
    @Bean
    public Step step2ProcessOrder() {
        // 1. 定义重试策略（和之前一致：最多重试3次，仅对RuntimeException重试）
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);

        // 2. 构建RetryTemplate（手动重试核心）
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy);

        // 3. 包裹Tasklet逻辑，实现重试
        Tasklet retryableTasklet = (contribution, chunkContext) -> {
            // 用RetryTemplate执行Tasklet逻辑，触发重试
            return retryTemplate.execute(context -> {
                return processOrderTasklet().execute(contribution, chunkContext);
            });
        };

        // 4. 构建Step（去掉faultTolerant()，无报错）
        return stepBuilderFactory.get("step2ProcessOrder")
                .tasklet(retryableTasklet) // 使用包裹后的重试Tasklet
                .build();
    }

    @Bean
    public Step step3ErrorHandle() {
        return stepBuilderFactory.get("step3ErrorHandle")
                .tasklet(errorHandleTasklet())
                .build();
    }

    @Bean
    public Step step4CleanResource() {
        return stepBuilderFactory.get("step4CleanResource")
                .tasklet(cleanResourceTasklet())
                .build();
    }

    // ====================== 3. 拆分Flow（核心：避免语法报错） ======================
    /**
     * Flow1：Step1的分支逻辑（COMPLETED/PART_SUCCESS→Step2，FAILED→Step3）
     */
    @Bean
    public Flow step1Flow() {
        return new FlowBuilder<Flow>("step1Flow")
                .start(step1InitOrder())
                .on("PART_SUCCESS").to(step4CleanResource())
                .on("COMPLETED").to(step2ProcessOrder())
                .on("FAILED").to(step3ErrorHandle())
                .end(); // 闭合Flow，必加
    }

    /**
     * Flow2：Step2→Step4（无论结果如何）
     */
    @Bean
    public Flow step2Flow() {
        return new FlowBuilder<Flow>("step2Flow")
                .start(step2ProcessOrder())
                .on("*").to(step4CleanResource()) // *匹配所有状态
                .end();
    }

    /**
     * Flow3：Step3→Step4（无论结果如何）
     */
    @Bean
    public Flow step3Flow() {
        return new FlowBuilder<Flow>("step3Flow")
                .start(step3ErrorHandle())
                .on("*").to(step4CleanResource())
                .end();
    }

    /**
     * Flow4：Step4结束
     */
    @Bean
    public Flow step4Flow() {
        return new FlowBuilder<Flow>("step4Flow")
                .start(step4CleanResource())
                .end();
    }

    // ====================== 4. 整合Flow构建Job（终极核心，无报错） ======================
    @Bean
    public Job orderProcessJob() {
        return jobBuilderFactory.get("orderProcessJobOld")
                .incrementer(new RunIdIncrementer())
                .start(step1InitOrder())
                .on("PART_SUCCESS").to(step2ProcessOrder())

                .from(step1InitOrder())
                .on("COMPLETED").to(step2ProcessOrder())

                .from(step1InitOrder())
                .on("FAILED").to(step3ErrorHandle())


                .from(step2ProcessOrder()).on("*").to(step4CleanResource())

                .from(step3ErrorHandle()).on("*").to(step4CleanResource())

                .from(step4CleanResource()).on("*").end()

                .build().build();
        // return jobBuilderFactory.get("orderProcessJob")
        //         .incrementer(new RunIdIncrementer())
        //         // 起点：step1
        //         .start(step1InitOrder())
        //         // 分支1：step1成功（COMPLETED）→ step2
        //         .on("COMPLETED").to(step2ProcessOrder())
        //         // 分支2：step1部分成功（PART_SUCCESS）→ step2
        //         .on("PART_SUCCESS").to(step2ProcessOrder())
        //         // 分支3：step1失败（FAILED）→ step3
        //         .on("FAILED").to(step3ErrorHandle())
        //         // ---------------- 关键：给step2/step3配置下一步 ----------------
        //         // step2无论结果如何 → step4
        //         .from(step2ProcessOrder()).on("*").to(step4CleanResource())
        //         // step3无论结果如何 → step4
        //         .from(step3ErrorHandle()).on("*").to(step4CleanResource())
        //         // step4执行完 → Job结束
        //         .from(step4CleanResource()).on("*")
        //         // 构建Job（核心：无需拆分Flow，直接链式配置）
        //         .end().build().build();
        // return jobBuilderFactory.get("orderProcessJob")
        //             .incrementer(new RunIdIncrementer())
        //             // 第一步：Step1的状态映射（仅保留必要映射，无冲突）
        //             .start(step1InitOrder())
        //             // 1. 自定义状态：PART_SUCCESS → Step2
        //             .on("PART_SUCCESS").to(step2ProcessOrder())
        //             // 2. 默认状态：COMPLETED → Step2
        //             .on("COMPLETED").to(step2ProcessOrder())
        //             // 3. 默认状态：FAILED → Step3
        //             .on("FAILED").to(step3ErrorHandle())
        //
        //             // 第二步：Step2 → Step4（唯一映射，无回退）
        //             .from(step2ProcessOrder()).on("*").to(step4CleanResource())
        //
        //             // 第三步：Step3 → Step4（唯一映射，无回退）
        //             .from(step3ErrorHandle()).on("*").to(step4CleanResource())
        //
        //             // 第四步：Step4 → 结束（唯一终止点，无其他逻辑）
        //             .from(step4CleanResource()).on("*").end()
        //
        //             // 构建Job（无任何多余兜底，避免循环）
        //             .build().build();
        }
    }
