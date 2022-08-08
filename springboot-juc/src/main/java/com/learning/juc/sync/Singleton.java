package com.learning.juc.sync;


/**
 * @Package: com.learning.helloworld.juc
 * @Description: Singleton 单例模式
 * synchronized：验证有序性
 * 如果在本线程内观察，所有的操作都是有序的;如果在一个线程观察另一个线程，所有的操作都是无序 的。
 * @Author: Sammy
 * @Date: 2022/8/6 16:41
 */

public class Singleton {
	private Singleton() {
	}

	private volatile static Singleton instance;

	public Singleton getSingleton() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
