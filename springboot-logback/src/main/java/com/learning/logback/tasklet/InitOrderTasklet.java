package com.learning.logback.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Step1：初始化订单数据，模拟随机异常，自定义ExitStatus
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/1/23 09:44]
 */

@Slf4j
public class InitOrderTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("\n===== Step1：初始化订单数据 =====");

		// 1. 生成10个订单ID
		List<Integer> orderIds = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			orderIds.add(i);
		}

		// 2. 随机生成异常订单数（0-3个）
		Random random = new Random();
		int errorCount = random.nextInt(4); // 0=无异常，1-2=部分成功，3=全部失败
		List<Integer> errorOrderIds = new ArrayList<>();
		for (int i = 0; i < errorCount; i++) {
			errorOrderIds.add(orderIds.get(random.nextInt(orderIds.size())));
		}

		// 3. 跨Step共享数据（Job上下文）
		chunkContext.getStepContext().getStepExecution()
				.getJobExecution().getExecutionContext()
				.put("allOrderIds", orderIds);
		chunkContext.getStepContext().getStepExecution()
				.getJobExecution().getExecutionContext()
				.put("errorOrderIds", errorOrderIds);

		// 4. 自定义ExitStatus
		ExitStatus exitStatus;
		if (errorCount == 0) {
			exitStatus = ExitStatus.COMPLETED;
			log.info("Step1执行完成：无异常订单，ExitStatus=" + exitStatus);
		} else if (errorCount < 3) {
			exitStatus = new ExitStatus("PART_SUCCESS"); // 自定义状态
			log.info("Step1执行完成：异常订单数=" + errorCount + "，ExitStatus=" + exitStatus);
		} else {
			exitStatus = ExitStatus.FAILED;
			log.info("Step1执行失败：异常订单数=" + errorCount + "，ExitStatus=" + exitStatus);
		}
		contribution.setExitStatus(exitStatus);

		// contribution.setExitStatus(new ExitStatus("PART_SUCCESS"));

		return RepeatStatus.FINISHED;
	}
}