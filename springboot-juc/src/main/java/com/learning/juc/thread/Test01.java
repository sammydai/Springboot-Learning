package com.learning.juc.thread;

/**
 * @Package: com.learning.juc.thread
 * @Description: Test01
 * @Author: Sammy
 * @Date: 2021/10/30 23:31
 */

public class Test01 {
	public static void main(String[] args) {
		Test01 obj = new Test01();
		Test01 obj2 = new Test01();
		new Thread(new Runnable() {
			@Override
			public void run() {
				obj.mm();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				obj2.mm();
			}
		}).start();
	}

	public void mm() {
		synchronized (this) {
			for (int i = 1; i <= 100; i++) {
				System.out.println(Thread.currentThread().getName() + " --> " + i);
			}
		}
	}

}
