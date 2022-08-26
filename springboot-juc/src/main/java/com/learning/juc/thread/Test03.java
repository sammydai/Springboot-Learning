package com.learning.juc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.learning.juc.thread
 * @Description: Test03
 * @Author: Sammy
 * @Date: 2021/10/30 23:52
 */

public class Test03 {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			
		}
	}

	static class MyThread extends Thread {
		public static AtomicInteger count = new AtomicInteger();

		public static void addCount() {
			for (int i = 0; i < 10000; i++) {
				count.getAndIncrement();
			}
			System.out.println(Thread.currentThread().getName() + " count=" + count.get());
		}

		@Override
		public void run() {
			addCount();
		}
	}


}
