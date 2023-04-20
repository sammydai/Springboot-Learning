package com.learning.juc.model;

/**
 * @Package: com.learning.juc.model
 * @Description: interview
 * @Author: Sammy
 * @Date: 2023/3/30 19:41
 */

public class Singleton {

	private static final Singleton instance = new Singleton();

	private Singleton() {

	}

	public Singleton getInstance() {
		return instance;
	}
}
