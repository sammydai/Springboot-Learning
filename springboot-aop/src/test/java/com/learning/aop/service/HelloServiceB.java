package com.learning.aop.service;

/**
 * @Package: com.learning.ioc.aop
 * @Description: BServiceImpl
 * @Author: Sammy
 * @Date: 2022/8/26 15:54
 */
public class HelloServiceB implements HelloService {

	@Override
	public void doSomething() {
		System.out.println("bbbb");
	}

}
