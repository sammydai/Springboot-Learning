package com.learning.logback.tasklet;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

/**
 * Step2：处理订单，模拟失败重试
 */
@Slf4j
public class ProcessOrderTasklet implements Tasklet {
    private int retryCount = 0; // 重试计数器

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        retryCount++;
        log.info("\n===== Step2：处理订单（第" + retryCount + "次执行） =====");

        // 1. 读取Job上下文数据
        List<Integer> allOrderIds = (List<Integer>) chunkContext.getStepContext()
                .getStepExecution().getJobExecution().getExecutionContext().get("allOrderIds");
        List<Integer> errorOrderIds = (List<Integer>) chunkContext.getStepContext()
                .getStepExecution().getJobExecution().getExecutionContext().get("errorOrderIds");

        // 2. 模拟前2次执行失败，第3次成功（触发重试）
        if (retryCount <= 2) {
            throw new RuntimeException("Step2处理订单失败（重试次数：" + retryCount + "）");
        }

        // 3. 处理正常订单，跳过异常订单
        int successCount = 0;
        for (Integer orderId : allOrderIds) {
            if (errorOrderIds.contains(orderId)) {
                log.info("跳过异常订单：ID=" + orderId);
                continue;
            }
            log.info("成功处理订单：ID=" + orderId);
            successCount++;
        }

        // 4. 存储处理结果
        chunkContext.getStepContext().getStepExecution()
                .getJobExecution().getExecutionContext().put("successCount", successCount);

        log.info("Step2执行完成：成功处理" + successCount + "个订单");
        return RepeatStatus.FINISHED;
    }

}