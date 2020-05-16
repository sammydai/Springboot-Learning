package com.dwt;

import java.util.Date;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/5 19:46
 */

public class Demo4 implements Runnable {

	private static int counter = 1;


	public synchronized static void method() {
		Date date = new Date();
			for (int i = 0; i < 5; i++) {
				 try {
                    System.out.println("线程 ：" + Thread.currentThread().getName() + " 当前计数器 ：" + (counter++));
                    System.out.println("开始时间 ：" + date + " 当前时间 ：" + System.currentTimeMillis());
                    System.out.println();
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
		}
	}

	@Override
	public void run() {
		method();
	}

	public static void main(String[] args) {
		Demo4 demo4 = new Demo4();
		Demo4 demo5 = new Demo4();
		Thread thread1 = new Thread(demo4, "sync-thread-1");
		Thread thread2 = new Thread(demo5, "sync-thread-2");
		thread1.start();
		thread2.start();
	}

}
