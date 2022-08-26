package com.learning.juc.sync;

/**
 * @Package: com.learning.helloworld.juc
 * @Description: ReentryTest
 * @Author: Sammy
 * @Date: 2022/8/6 16:54
 */

public class ReentryTest extends A {

	public static void main(String[] args) {
		ReentryTest reentry = new ReentryTest();
		reentry.doA();
	}

	public synchronized void doA() {
		System.out.println("子类方法:ReentryTest.doA() ThreadId:" + Thread.currentThread().getId());
		doB();
	}

	private synchronized void doB() {
		super.doA();
		System.out.println("子类方法:ReentryTest.doB() ThreadId: " + Thread.currentThread().getId());
	}
}

class A {
	public synchronized void doA() {
		System.out.println("父类方法:A.doA() ThreadId: " + Thread.currentThread().getId());
	}
}


