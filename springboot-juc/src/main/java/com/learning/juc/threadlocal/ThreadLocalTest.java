package com.learning.juc.threadlocal;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 15:22]
 */
public class ThreadLocalTest {
	String localVar1 = "123";
	String localVar2 = "local";
	Integer localVar3 = 1;

	ThreadLocal<String> threadLocalObject1 = new ThreadLocal<String>();
	ThreadLocal<Object> threadLocalObject2 = new ThreadLocal<Object>();
	ThreadLocal<Integer> threadLocalObject3 = new ThreadLocal<Integer>();


	public void setLocalThread() {
		threadLocalObject1.set(localVar1);
		threadLocalObject2.set(localVar2);
		threadLocalObject3.set(localVar3);
	}

	public void getLocalThread() {
		System.out.println(Thread.currentThread().getName() + threadLocalObject1.get());
		System.out.println(Thread.currentThread().getName() + threadLocalObject2.get());
		System.out.println(Thread.currentThread().getName() + threadLocalObject3.get());
	}

	public static void main(String[] args) {
		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		threadLocalTest.setLocalThread();
		threadLocalTest.getLocalThread();
	}

}
