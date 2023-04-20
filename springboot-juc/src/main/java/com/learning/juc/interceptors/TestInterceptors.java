package com.learning.juc.interceptors;

/**
 * @Package: com.learning.juc.interceptors
 * @Description:
 * @Author: Sammy
 * @Date: 2022/8/6 21:54
 */

public class TestInterceptors {
	public static void main(String[] args) {
		HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(), "com.learning.juc.interceptors.MyInterceptor");
		proxy.sayHelloWorld();
	}
}
