package com.learning.juc.future;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Package: com.learning.juc.future
 * @Description: Completable Future Test Set 3.33
 * @Author: Sammy
 * @Date: 2020/12/4 14:03
 */

public class CompletableFutureSet {
	//创建线程池
	private static final ThreadPoolExecutor bizPool = new ThreadPoolExecutor(8, 8, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

	private CompletableFuture<String> futureA = futureAMethod();

	public CompletableFuture<String> futureAMethod() {
		CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello methodA";
		});
		return futureA;
	}

	/**
	 * 执行异步/无返回值操作
	 */
	@Test
	public void runAsync() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("over");
		});
		//同步等待异步执行任务结束
		System.out.println(future.get());
	}

	/**
	 * 异步方法，使用自己的线程池 biz
	 *
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void runAsyncwithBiz() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("over");
		}, bizPool);
		//同步等待异步执行任务结束
		System.out.println(future.get());
	}

	/**
	 * 异步方法，有返回值
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void supplySync() throws ExecutionException, InterruptedException {
		CompletableFuture future = CompletableFuture.supplyAsync(() -> {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "hello,yibu operation";
				}
		);
		System.out.println(future.get());
	}

	/**
	 * 异步执行A, A执行完后，触发B异步任务
	 * thenRun() B任务拿不到A的执行结果
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void thenRun() throws ExecutionException, InterruptedException {

		CompletableFuture<Void> futureB = futureA.thenRun(() -> {
			System.out.println(Thread.currentThread().getName());
			System.out.println("----after methodA over doSomething----");
		});
		System.out.println(futureB.get());
	}

	/**
	 * 异步执行A, A执行完后，触发B异步任务
	 * thenAccept() B任务能拿到A的执行结果
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void thenAccept() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> futureB = futureA.thenAccept(ss->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("----after methodA over doSomething----"+ss);
		});
		System.out.println(futureB.get());
	}

	/**
	 * 异步执行完A任务，激活异步任务B执行
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void thenApply() throws ExecutionException, InterruptedException {
		CompletableFuture<String> futureB = futureA.thenApply(ss->{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "methodA: " + ss + " methodB:helloB";
		});
		System.out.println(futureB.get());
	}

	@Test
	public void whenComplete() throws InterruptedException {
		CompletableFuture<String> futureB = futureA.whenComplete((ss, ex) -> {
			if (ex == null) {
				System.out.println("====>" + ss);
			} else {
				System.out.println(ex.getLocalizedMessage());
			}
		});
		Thread.currentThread().join();
	}
}