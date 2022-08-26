package com.learning.juc.interceptors;

/**
 * @Package: com.learning.juc.interceptors
 * @Description: HelloWorldImpl
 * @Author: Sammy
 * @Date: 2022/8/6 21:53
 */

public class HelloWorldImpl implements HelloWorld{
	@Override
	public void sayHelloWorld() {
		System.out.println("Hello World");
	}
}
