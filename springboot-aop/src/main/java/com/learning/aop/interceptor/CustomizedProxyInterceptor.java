package com.learning.aop.interceptor;


import com.learning.aop.handler.ProxyBeanHandler;
import com.learning.aop.util.ConfigurationUtil;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 15:59]
 */
public class CustomizedProxyInterceptor implements MethodInterceptor {

	List<ProxyBeanHandler> proxyBeanHandlerList;

	public CustomizedProxyInterceptor(List<ProxyBeanHandler> proxyBeanHandlerList) {
		this.proxyBeanHandlerList = proxyBeanHandlerList;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		//处理前置及环绕前置通知
		for (ProxyBeanHandler proxyBeanHandler : proxyBeanHandlerList) {
			String annotationName = proxyBeanHandler.getAnnotationName();
			if ((annotationName.equals(ConfigurationUtil.BEFORE)) || (annotationName.equals(ConfigurationUtil.AROUND))) {
				this.doProxy(proxyBeanHandler);
			}
		}
		Object result = null;
		try {
			result = methodProxy.invokeSuper(o, args);
		} catch (Exception e) {
			System.out.println("get ex:" + e.getMessage());
			throw e;
		}
		//处理后置及环绕前置通知
		for (ProxyBeanHandler proxyBeanHandler : proxyBeanHandlerList) {
			String annotationName = proxyBeanHandler.getAnnotationName();
			if ((annotationName.equals(ConfigurationUtil.AFTER)) || (annotationName.equals(ConfigurationUtil.AROUND))) {
				this.doProxy(proxyBeanHandler);
			}
		}
		return result;
	}

	private void doProxy(ProxyBeanHandler proxyBeanHandler) {
		String className = proxyBeanHandler.getClassName();
		String methodName = proxyBeanHandler.getMethodName();
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
			Method[] methods = clazz.getMethods();
			for (Method poxyMethod : methods) {
				if (poxyMethod.getName().equals(methodName)) {
					poxyMethod.invoke(clazz.newInstance());
				}
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}
}
