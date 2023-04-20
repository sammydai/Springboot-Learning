package com.learning.juc.thread;

/**
 * @Package: com.learning.juc.thread
 * @Description:
 * @Author: Sammy
 * @Date: 2023/3/30 16:14
 */

public class Test07 {

	private static final Object lock = new Object();

	private static int ticks = 5;

	static class Thread0 extends Thread {
		@Override
		public void run() {
			while (true) {
				if (ticks > 0) {
					synchronized (lock) {
						lock.notifyAll();
						System.out.println(Thread.currentThread().getName() + " 抢到了第" + ticks + "张票");
						ticks--;
						try {
							lock.wait();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				} else {
					break;
				}
			}
		}
	}


	public static void main(String[] args) {
		Thread0 target = new Thread0();
		Thread thread0 = new Thread(target, "小红");
		Thread thread1 = new Thread(target, "小黄");
		Thread thread2 = new Thread(target, "小钢");

		thread0.start();
		thread1.start();
		thread2.start();
	}
}
