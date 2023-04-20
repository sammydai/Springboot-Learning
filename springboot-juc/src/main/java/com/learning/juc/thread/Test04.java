package com.learning.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.learning.juc.thread
 * @Description: interview
 * @Author: Sammy
 * @Date: 2023/3/30 13:53
 */

public class Test04 {
	public static void main(String[] args) {
		Number number = new Number();
		// NumberNew number = new NumberNew();
		Thread thread1 = new Thread(number, "Thread-1");
		Thread thread2 = new Thread(number, "Thread-2");
		thread1.start();
		thread2.start();
	}
}

class Number implements Runnable {

	private int i = 0;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				//唤醒另外一个等待的线程
				notify();
				if (i < 100) {
					i++;
					System.out.println(Thread.currentThread().getName() + "---" + i);
					try {
						//会释放锁
						wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}

				} else {
					break;
				}
			}
		}
	}
}

class NumberNew implements Runnable {

	private int i = 0;
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	@Override
	public void run() {
		while (true) {
			lock.lock();
			if (i < 100) {
				condition.signal();
				i++;
				System.out.println(Thread.currentThread().getName() + "--" + i);
				try {
					condition.await();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			} else {
				break;
			}
			lock.unlock();
		}
	}
}

