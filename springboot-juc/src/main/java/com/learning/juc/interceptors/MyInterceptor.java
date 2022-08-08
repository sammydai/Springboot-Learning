package com.learning.juc.interceptors;

import java.lang.reflect.Method;

/**
 * @Package: com.learning.juc.interceptors
 * @Description: MyInterceptor
 * @Author: Sammy
 * @Date: 2022/8/6 21:42
 */

public class MyInterceptor implements Interceptor{
	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.err.println("反射方法前逻辑");
		return true;
	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.err.println("取代了被代理对象的方法");

	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.err.println("反射方法后逻辑");

	}
}
