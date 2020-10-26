package com.dwt;


import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/21 09:23
 */

@Slf4j
@RestController
@SpringBootApplication
public class PoolDemo {

	private void printStats(ThreadPoolExecutor threadPool) {

		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
			log.info("=========================");
			log.info("Pool Size: {}", threadPool.getPoolSize());
			log.info("Active Threads: {}", threadPool.getActiveCount());
			log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
			log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

			log.info("=========================");
		}, 0, 1, TimeUnit.SECONDS);
	}


	@GetMapping(value="oom1")
	public void oom1() throws InterruptedException {

		ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
		//打印线程池的信息，稍后我会解释这段代码
		printStats(threadPool);
		for (int i = 0; i < 100000000; i++) {
			threadPool.execute(() -> {
				String payload = IntStream.rangeClosed(1, 1000000)
						.mapToObj(__ -> "a")
						.collect(Collectors.joining("")) + UUID.randomUUID().toString();
				try {
					TimeUnit.HOURS.sleep(1);
				} catch (InterruptedException e) {
				}
				log.info(payload);
			});
		}

		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.HOURS);
	}


	@GetMapping("right")
	public int right() throws InterruptedException {
		//使用一个计数器跟踪完成的任务数
		AtomicInteger atomicInteger = new AtomicInteger();
		//创建一个具有2个核心线程、5个最大线程，使用容量为10的ArrayBlockingQueue阻塞队列作为工作队列的线程池，使用默认的AbortPolicy拒绝策略
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
				2,
				5,
				5, TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(10),
				new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get(),
				new ThreadPoolExecutor.AbortPolicy());

		printStats(threadPool);
		//每隔1秒提交一次，一共提交20次任务
		IntStream.rangeClosed(1, 20).forEach(i -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int id = atomicInteger.incrementAndGet();
			try {
				threadPool.submit(() -> {
					log.info("{} started", id);
					//每个任务耗时10秒
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
					}
					log.info("{} finished", id);
				});
			} catch (Exception ex) {
				//提交出现异常的话，打印出错信息并为计数器减一
				log.error("error submitting task {}", id, ex);
				atomicInteger.decrementAndGet();
			}
		});

		TimeUnit.SECONDS.sleep(60);
		return atomicInteger.intValue();
	}

	public static void main(String[] args) {
		SpringApplication.run(PoolDemo.class, args);
	}
}
