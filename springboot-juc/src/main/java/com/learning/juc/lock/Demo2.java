package com.learning.juc.lock;

/**
 * @Package: com.learning.juc.lock
 * @Description:
 * @Author: Sammy
 * @Date: 2022/9/23 16:16
 */

public class Demo2 implements Runnable {

	//票数 static和synchronized

	private volatile int ticketNum = 100;

	@Override
	public void run() {
		int count = 0;
		//获取当前时间
		long time1 = System.currentTimeMillis();

		//具体的卖票逻辑
		while (true) {
			synchronized (Demo2.class) {
				if (ticketNum > 0) {
					System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNum + "票");
					ticketNum--;
					count++;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					long time2 = System.currentTimeMillis();
					System.out.println("本次程序运行时间为： " + (time2 - time1));
					System.out.println("本次程序运行次数为" + count);
					break;
				}
			}
		}
	}


	public static void main(String[] args) {

		Demo2 t1 = new Demo2();
		Demo2 t2 = new Demo2();
		Demo2 t3 = new Demo2();

		new Thread(t1, "张三").start();
		new Thread(t1, "李四").start();
		new Thread(t1, "王五").start();
	}

}

