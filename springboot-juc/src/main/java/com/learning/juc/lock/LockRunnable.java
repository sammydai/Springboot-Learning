package com.learning.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.learning.juc.utils;
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/23 11:17
 */

public class LockRunnable implements Runnable {

	private int num;

	private static final Object lock = new Object();

	public LockRunnable(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (lock) {
				if (num < 0) {
					break;
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "  " + num--);
			}
		}
	}

	public static void main(String[] args) {
		final ReentrantLock reentrantLock = new ReentrantLock();
		final Condition condition = reentrantLock.newCondition();
		new Thread(() -> {
			reentrantLock.lock();
			System.out.println(Thread.currentThread().getName() + "拿到锁了");
			System.out.println(Thread.currentThread().getName() + "等待信号");
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "拿到信号");
			reentrantLock.unlock();
		}, "线程1").start();

		new Thread(() -> {
			reentrantLock.lock();
			System.out.println(Thread.currentThread().getName() + "拿到锁了");

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "发出信号");
			condition.signalAll();
			reentrantLock.unlock();
		}, "线程2").start();
	}
}

