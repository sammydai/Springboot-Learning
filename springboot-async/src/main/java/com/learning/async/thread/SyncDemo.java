package com.learning.async.thread;

import java.util.concurrent.*;

/**
 * @Package: com.learning.async.thread
 * @Description: Java Asynchronous Programming In Action C2
 * @Author: Sammy
 * @Date: 2020/11/22 11:59
 */

public class SyncDemo {
	private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR =
			new ThreadPoolExecutor(AVAILABLE_PROCESSORS,
					AVAILABLE_PROCESSORS * 2,
					1,
					TimeUnit.MINUTES,
					new LinkedBlockingQueue<>(5),
					new NamedThreadFactory("ASYNC-POOL"),
					new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// long start = System.currentTimeMillis();
		Future<?> resultA = THREAD_POOL_EXECUTOR.submit(()->{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("--------doSomethingA-------");
			return "A Task Done.";
		});
		System.out.println(resultA.get());
		// System.out.println(System.currentTimeMillis() - start);
		// Thread.currentThread().join();
	}

	public static void createThreadMethod() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				doSomethingA();
			}
		},"threadA");
		thread.start();
		// doSomethingA();
		doSomethingB();
		thread.join();
		System.out.println(System.currentTimeMillis() - start);
	}

	public static String doSomethingA()  {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------doSomethingA-------");
		return "A Task Done.";
	}

	public static String doSomethingB() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------doSomethingB-------");
		return "B Task Done.";

	}
}

class doSomething implements Callable<String>{
	@Override
	public String call() throws Exception {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------doSomethingCCC-------");
		return "CCC Task Done.";
	}
}
