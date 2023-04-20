package com.learning.juc.thread;

/**
 * @Package: com.learning.juc.thread
 * @Description: interview
 * @Author: Sammy
 * @Date: 2023/3/30 14:40
 */

public class Test06 {

	private static volatile int COUNTER = 0;
	//计数器
	private static final Object lock = new Object();
	//定义一个锁对象

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (lock) {
					// lock.notifyAll();
					while (COUNTER % 3 != 0) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
					System.out.println(Thread.currentThread().getName());
					COUNTER++;
					lock.notifyAll();
				}
			}
		}, "A");
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (lock) {
					// lock.notifyAll();
					while (COUNTER % 3 != 1) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
					System.out.println(Thread.currentThread().getName());
					COUNTER++;
					lock.notifyAll();
				}
			}
		}, "B");
		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (lock) {
					while (COUNTER % 3 != 2) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
					System.out.println(Thread.currentThread().getName());
					COUNTER++;
					lock.notifyAll();
				}
			}
		}, "C");
		t1.start();
		t2.start();
		t3.start();
	}
}


