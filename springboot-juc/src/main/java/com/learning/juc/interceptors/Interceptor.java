package com.learning.juc.interceptors;

import java.lang.reflect.Method;

/**
 * @Package: com.learning.juc.interceptors
 * @Description: Interceptor
 * @Author: Sammy
 * @Date: 2022/8/6 21:40
 */

public interface Interceptor {

	public boolean before(Object proxy, Object target, Method method, Object[] args);

	public void around(Object proxy, Object target, Method method, Object[] args);

	public void after(Object proxy, Object target, Method method, Object[] args);

}
