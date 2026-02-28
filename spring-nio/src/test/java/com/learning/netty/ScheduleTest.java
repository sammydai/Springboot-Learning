package com.learning.netty;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/20 13:36]
 */
public class ScheduleTest {
	public static void main(String[] args) {
		ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
		ListenableFuture<Long> listenableFuture = listeningExecutorService.submit(new FibonacciTask(10));
		Futures.addCallback(listenableFuture, new FutureCallback<Long>() {
			@Override
			public void onSuccess(@Nullable Long result) {
				// 异步任务成功完成时执行的操作
				System.out.println("Fibonacci number at position 10 is: " + result);
				listeningExecutorService.shutdown(); // 关闭 executorService
			}

			@Override
			public void onFailure(Throwable t) {
				// 	// 异步任务失败时执行的操作
				System.err.println("Async task failed: " + t.getMessage());
				listeningExecutorService.shutdown(); // 关闭 executorService
			}
		}, listeningExecutorService);
		System.out.println("======?==>");
	}

	public void methodA() {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		scheduledExecutorService.scheduleWithFixedDelay(() -> {
			System.out.println("=========>schedule");
		}, 4000, 1000, TimeUnit.MILLISECONDS);

	}

}

	 class FuturesExample {
		public static void main(String[] args) {
			// 创建 ListeningExecutorService
			ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

			// 创建多个 ListenableFuture 任务
			ListenableFuture<String> future1 = executorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					return "Result of Future 1";
				}
			});

			ListenableFuture<String> future2 = executorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					throw new RuntimeException("Future 2 failed");
				}
			});

			ListenableFuture<String> future3 = executorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					return "Result of Future 3";
				}
			});

			// 使用 Futures.successfulAsList 处理所有任务
			ListenableFuture<List<String>> allFutures = Futures.successfulAsList(Arrays.asList(future1, future2, future3));

			Futures.addCallback(allFutures, new FutureCallback<List<String>>() {
				@Override
				public void onSuccess(List<String> results) {
					System.out.println("All futures completed successfully");
					for (int i = 0; i < results.size(); i++) {
						System.out.println("Result " + (i + 1) + ": " + results.get(i));
					}
					executorService.shutdown(); // 关闭 executorService
				}

				@Override
				public void onFailure(Throwable t) {
					System.err.println("An error occurred: " + t.getMessage());
					executorService.shutdown(); // 关闭 executorService
				}
			}, executorService);
		}
	}


