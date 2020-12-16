package com.learning.juc.sync;

/**
 * @Package: com.learning.juc.sync
 * @Description: Interrupt Demo
 * @Author: Sammy
 * @Date: 2020/11/30 14:33
 */

public class InterruptDemo extends Thread {
	boolean stop = false;

	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(new InterruptDemo(), "My Thread");
		System.out.println("Starting thread...");
		thread.start();
		Thread.sleep(3000);
		// System.out.println("Interrupting thread...");
		System.out.println("Asking thread to stop...");
		thread.interrupt();
		System.out.println("线程是否中断：" + thread.isInterrupted());
		Thread.sleep(3000);
		System.out.println("Stopping application...");
	}

	// public void run() {
	// 	while (!stop) {
	// 		System.out.println("My Thread is running...");
	// 		// 让该循环持续一段时间，使上面的话打印次数少点
	// 		long time = System.currentTimeMillis();
	// 		while ((System.currentTimeMillis() - time < 1000)) {
	// 		}
	// 		//判断如果线程被中断了，就直接返回
	// 		if (Thread.currentThread().isInterrupted()) {
	// 			return;
	// 		}
	// 	}
	// 	System.out.println("My Thread exiting under request...");
	// }


	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("Thread running...");
			try {
				// 线程阻塞
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted...");
				 /*
                 * 如果线程在调用 Object.wait()方法，或者该类的 join() 、sleep()方法
                 * 过程中受阻，则其中断状态将被清除
                 */
                System.out.println(this.isInterrupted());// false
				//中不中断由自己决定，如果需要真真中断线程，则需要重新设置中断位，如果
                //不需要，则不用调用
                Thread.currentThread().interrupt();
			}
		}
		System.out.println("Thread exiting under request...");
	}
}



