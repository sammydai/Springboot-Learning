package com.learning.juc.utils;

import java.util.concurrent.Semaphore;

/**
 * @Package: com.learning.juc.utils
 * @Description: SemaphoreDemo
 * @Author: Sammy
 * @Date: 2022/8/6 18:17
 */

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2, false);
		for (int i = 0; i < 8; i++) {
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "排队摩天轮");
					Thread.sleep(1000L);
				} catch (InterruptedException ignore) {
				} finally {
					semaphore.release();
				}
			}, "摩天轮编号:" + i).start();
		}
	}
}
