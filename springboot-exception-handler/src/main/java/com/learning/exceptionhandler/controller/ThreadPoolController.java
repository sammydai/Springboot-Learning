package com.learning.exceptionhandler.controller;

import jodd.util.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Package: com.learning.exceptionhandler.controller
 * @Description: ThreadPoolController
 * @Author: Sammy
 * @Date: 2020/12/18 12:29
 */
@Slf4j
@RestController
public class ThreadPoolController {

	@GetMapping("execute")
	public void execute() throws InterruptedException {
		String prefix = "test";
		ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryBuilder().withNameFormat(prefix + "%d").get());
		IntStream.rangeClosed(1,100).forEach(i->threadPool.execute(()->{
			if (i==5) {
				throw new RuntimeException("error");
			}
			log.info("I'm done: {}",i);
		}));
		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.HOURS);
	}

	//execute方法，可以正常捕获到自定义异常，出现异常会导致线程退出
	@GetMapping("executeright")
	public void executeright() throws InterruptedException {
		String prefix = "test";
		ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryBuilder()
				.withNameFormat(prefix + "%d")
				.withUncaughtExceptionHandler((thread,throwable)-> log.error("ThreadPool {} got exception", thread, throwable))
				.get());
		IntStream.rangeClosed(1,10).forEach(i->threadPool.execute(()->{
			if (i==5) {
				throw new RuntimeException("error");
			}
			log.info("I'm done: {}",i);
		}));
		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.HOURS);
	}

	//submit方法 线程没有退出 一直是test0，异常记录
	@GetMapping("executesumbit")
	public void executesumbit() throws InterruptedException {
		String prefix = "test";
		ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryBuilder()
				.withNameFormat(prefix + "%d")
				.withUncaughtExceptionHandler((thread,throwable)-> log.error("ThreadPool {} got exception", thread, throwable))
				.get());
		List<Future> tasks = IntStream.rangeClosed(1, 10).mapToObj(i -> threadPool.submit(() -> {
			if (i == 5) {
				throw new RuntimeException("error");
			}
			log.info("I'm done: {}", i);
		})).collect(toList());
		tasks.forEach(task->{
			try {
				task.get();
			}  catch (Exception e) {
				log.error("Got exception", e);
			}
		});
		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.HOURS);
	}
}
