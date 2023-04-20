package com.learning.juc.thread;

/**
 * @Package: com.learning.juc.thread
 * @Description: interview
 * @Author: Sammy
 * @Date: 2023/3/30 14:17
 */

public class Test05 {
	public static void main(String[] args) {
		PrintNum number = new PrintNum();
		Thread thread1 = new Thread(number, "Thread-1");
		Thread thread2 = new Thread(number, "Thread-2");
		thread1.start();
		thread2.start();
	}
}

class PrintNum implements Runnable {

	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "--" + i);
			}
		}
	}
}
