package com.learning.juc.sync;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @Package: com.learning.helloworld.juc
 * @Description: ApiTest
 * @Author: Sammy
 * @Date: 2022/8/6 16:03
 */

public class ApiTest {

	private static volatile int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(() -> {
				for (int i1 = 0; i1 < 10000; i1++) {
					add();
				} });
			thread.start();
		}
		Thread.sleep(1000);
		System.out.println(counter);
	}

	/**
	 * 这段代码开启了 10 个线程来累加 counter，按照预期结果应该是 100000。
	 * 但 实际运行会发现，
	 * counter 值每次运行都小于 10000，
	 * 这是因为 volatile 并不 能保证原子性，
	 * 所以最后的结果不会是 10000。
	 */
	public static void add() {
		synchronized (ApiTest.class) {
			counter++;
		}
	}

	public void printHexMode(String[] args) {
		System.out.println(VM.current().details());
		Object obj = new Object();
		System.out.println(obj + " 十六进制哈希:" + Integer.toHexString(obj.hashCode()));
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}

	public void volatileTest(String[] args) {
		final VT vt = new VT();

		Thread thread01 = new Thread(vt);
		Thread thread02 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("vt.sign = true 通知 while (!sign) 结束!");
				vt.sign = true;
			}
		});
		thread01.start();
		thread02.start();
	}
}

class VT implements Runnable{

	public volatile boolean sign = false;

	@Override
	public void run() {
		while (!sign) {

		}
		System.out.println("aaa");
	}
}
