package com.learning.juc.sync;

/**
 * @Package: com.learning.juc.sync
 * @Description: InterruptDemo2
 * @Author: Sammy
 * @Date: 2020/11/30 15:21
 */

public class InterruptDemo2 extends Thread{

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("i=" + (i + 1));
			if (this.isInterrupted()) {
				System.out.println("通过this.isInterrupted()检测到中断");
				System.out.println("第一个interrupted()"+this.interrupted());
				System.out.println("第二个interrupted()"+this.interrupted());
				break;
			}
		}
		System.out.println("因为检测到中断，所以跳出循环，线程到这里结束，因为后面没有内容了");
	}

	public static void main(String[] args) throws InterruptedException {
		InterruptDemo2 myThread = new InterruptDemo2();
		myThread.start();
		myThread.interrupt();
		//sleep等待一秒，等myThread运行完
		Thread.currentThread().sleep(1000);
		System.out.println("myThread线程是否存活：" + myThread.isAlive());
	}
}
