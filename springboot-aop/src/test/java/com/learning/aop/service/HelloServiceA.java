package com.learning.aop.service;

/**
 * @Package: com.learning.ioc.aop
 * @Description: AServiceImpl
 * @Author: Sammy
 * @Date: 2022/8/26 15:53
 */
public class HelloServiceA implements HelloService {

	@Override
	public void doSomething() {
		System.out.println("aaaa");
	}

}
