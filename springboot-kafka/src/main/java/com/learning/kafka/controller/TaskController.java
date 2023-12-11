package com.learning.kafka.controller;

import com.learning.kafka.domain.User;
import com.learning.kafka.interceptor.ThreadPool;
import com.learning.kafka.interceptor.ThreadPoolInterceptor;
import com.learning.kafka.service.UserCallableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/11 17:03]
 */
@Slf4j
@RestController
public class TaskController {

	/**
	 * spring 提供的线程池
	 */
	@Autowired
	ThreadPoolTaskExecutor commonTaskExecutor;

	@Autowired
	ThreadPoolTaskExecutor statisticsDataExecutor;


	@RequestMapping("/def")
	@ThreadPool("threadA")
	public void sendMessageSnd2(@RequestBody User user) {
		List<FutureTask<Integer>> futureTasks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			UserCallableService callableService = new UserCallableService(i + " ");
			FutureTask<Integer> userFutureTask = new FutureTask<>(callableService);
			futureTasks.add(userFutureTask);
			ThreadPoolInterceptor.poolcontainer.get("threadA").submit(userFutureTask);
		}
		System.out.println("所有【计算任务】提交完毕，主线程开始执行");
		System.out.println("【主线程任务】开始============");
		try {
			Thread.sleep(5000);
			System.out.println("【主线程任务】开始执行某些任务============");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("【主线程任务】结束============");
		//用于打印任务执行结果
		Integer result = 0;
		for (FutureTask<Integer> task : futureTasks) {
			try {
				//FutureTask的get()方法会自动阻塞，知道得到任务执行结果为止
				result += task.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("多线程多任务执行结果：" + result);
	}

	@RequestMapping("/common")
	public void sendMessageSnd3(@RequestBody User user) {
		for (int i = 0; i < 5; i++) {
			commonTaskExecutor.execute(() -> {
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println("thread name: " + Thread.currentThread().getName());
			});
		}
	}

	@RequestMapping("/data")
	public void sendMessageSnd4(@RequestBody User user) {
		for (int i = 0; i < 5; i++) {
			statisticsDataExecutor.execute(() -> {
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println("thread name: " + Thread.currentThread().getName());
			});
		}
	}
}
