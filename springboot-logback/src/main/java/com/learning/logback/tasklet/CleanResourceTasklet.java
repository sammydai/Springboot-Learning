package com.learning.logback.tasklet;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Step4：资源清理（无论前面步骤结果如何，必执行）
 */
@Slf4j
public class CleanResourceTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("\n===== Step4：资源清理（必执行） =====");

        // 读取处理结果
        Integer successCount = (Integer) chunkContext.getStepContext()
                .getStepExecution().getJobExecution().getExecutionContext().get("successCount");
        successCount = successCount == null ? 0 : successCount;

        // 模拟清理逻辑
        log.info("清理临时订单文件...");
        log.info("关闭数据库连接...");
        log.info("本次批处理总计成功处理" + successCount + "个订单");
        log.info("===== 批处理流程结束 =====");

        return RepeatStatus.FINISHED;
    }
}