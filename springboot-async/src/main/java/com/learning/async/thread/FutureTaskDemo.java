package com.learning.async.thread;

import java.util.concurrent.*;

/**
 * @Package: com.learning.async.thread
 * @Description: Java Asynchronous Programming In Action C3
 * @Author: Sammy
 * @Date: 2020/11/22 13:06
 */

public class FutureTaskDemo {
	private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR =
			new ThreadPoolExecutor(AVAILABLE_PROCESSORS,
					AVAILABLE_PROCESSORS * 2,
					1,
					TimeUnit.MINUTES,
					new LinkedBlockingQueue<>(5),
					new NamedThreadFactory("ASYNC-POOL"),
					new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		long startTime = System.currentTimeMillis();
		Future<String> futureTask = THREAD_POOL_EXECUTOR.submit(() -> {
			String result = null;
			result = doSomethingA();
			return result;
		});
		String taskBResult = doSomethingB();
		String taskAResult = futureTask.get();
		System.out.println(taskAResult + " " + taskBResult);
		System.out.println(System.currentTimeMillis() - startTime);
	}

	public void method1() throws ExecutionException, InterruptedException {
		long startTime = System.currentTimeMillis();
		//异步任务
		FutureTask<String> futureTask = new FutureTask<>(() -> {
			String result = null;
			result = doSomethingA();
			return result;
		});
		Thread thread = new Thread(futureTask, "threadA");
		thread.start();
		String taskBResult = doSomethingB();
		String taskAResult = futureTask.get();
		System.out.println(taskAResult + " " + taskBResult);
		System.out.println(System.currentTimeMillis() - startTime);
	}

	public static String doSomethingA()  {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------doSomethingA-------");
		return "TaskAResult";
	}

	public static String doSomethingB() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------doSomethingB-------");
		return "TaskBResult";
	}
}
