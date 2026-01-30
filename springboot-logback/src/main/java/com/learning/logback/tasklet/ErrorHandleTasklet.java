package com.learning.logback.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

/**
 * Step3：异常处理（Step1失败时执行）
 */
@Slf4j
public class ErrorHandleTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("\n===== Step3：订单处理异常兜底 =====");

        // 读取异常订单信息
        List<Integer> errorOrderIds = (List<Integer>) chunkContext.getStepContext()
                .getStepExecution().getJobExecution().getExecutionContext().get("errorOrderIds");

        log.info("异常订单数量：" + errorOrderIds.size());
        log.info("异常订单ID：" + errorOrderIds);
        log.info("执行兜底逻辑：通知运维人员处理");

        return RepeatStatus.FINISHED;
    }

}