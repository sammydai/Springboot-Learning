package com.learning.juc.guava;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;

import java.util.concurrent.*;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/7/12 11:35]
 */
public class DoTaskDemo {

	public static void main(String[] args) {
		// test1();
		test();
	}

	private static void test1() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		SimpleTimeLimiter timeLimiter = SimpleTimeLimiter.create(executorService);
		UserService userService = timeLimiter
				.newProxy(new UserServiceImpl(), UserService.class, 1500, TimeUnit.MILLISECONDS);

		try {
			String name = userService.getName();
			System.out.println("name=" + name);
		} catch (Exception ex) {
			if (ex instanceof UncheckedTimeoutException) {
				System.out.println("超时：" + ex.getMessage());
			} else {
				System.out.println(ex.toString());
			}
		} finally {
			executorService.shutdown();
		}
	}

	/**
	 * 演示线程超时管理
	 */
	private static void test() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		TimeLimiter timeLimiter = SimpleTimeLimiter.create(executorService);
		try {
			String result = timeLimiter.callWithTimeout(new Callable<String>() {
				@Override
				public String call() throws Exception {
					TimeUnit.MILLISECONDS.sleep(2000);
					return "执行成功";
				}
			}, 3000, TimeUnit.MILLISECONDS);
			System.out.println("线程执行结果:" + result);
		} catch (TimeoutException e) {
			System.out.println("超时:" + e.getMessage());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		finally {
			executorService.shutdown();
		}
	}
}
