package com.dwt;

import java.util.Date;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/5 19:46
 */

public class Demo3 implements Runnable {

	private static int counter = 1;

	@Override
	public void run() {
		Date date = new Date();
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				 try {
                    System.out.println("线程 ：" + Thread.currentThread().getName() + " 当前计数器 ：" + (counter++));
                    System.out.println("开始时间 ：" + date + " 当前时间 ：" + System.currentTimeMillis());
                    System.out.println();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
		}

	}

	public static void main(String[] args) {
		Demo3 demo3 = new Demo3();
		Thread thread1 = new Thread(demo3, "sync-thread-1");
		Thread thread2 = new Thread(demo3, "sync-thread-2");
		thread1.start();
		thread2.start();
	}

}
