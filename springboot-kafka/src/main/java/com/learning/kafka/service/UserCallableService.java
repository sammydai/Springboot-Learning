package com.learning.kafka.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * UserCallableService
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/11 15:53]
 */
@Slf4j
public class UserCallableService implements Callable<Integer> {
	private final String taskName;//任务名称

	//任务构造器
	public UserCallableService(String taskName) {
		this.taskName = taskName;
		System.out.println("创建【计算任务】开始，计算任务名称：" + taskName);
	}


	@Override
	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum = sum + i;
		}
		System.out.println("【计算任务】" + taskName + "执行完成。");
		return sum;

	}
}
