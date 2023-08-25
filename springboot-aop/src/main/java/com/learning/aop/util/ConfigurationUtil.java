package com.learning.aop.util;


import com.learning.aop.handler.ProxyBeanHandler;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 14:20]
 */
public class ConfigurationUtil {
	/**
	 * aop标识注解类
	 */
	public static final String AOP_POINTCUT_ANNOTATION = "com.learning.aop.annotation.MyAspect";
	/**
	 * 前置通知注解类
	 */
	public static final String BEFORE = "com.learning.aop.annotation.MyBefore";
	/**
	 * 后置通知注解类
	 */
	public static final String AFTER = "com.learning.aop.annotation.MyAfter";
	/**
	 * 环绕通知注解类
	 */
	public static final String AROUND = "com.learning.aop.annotation.MyAround";


	/**
	 * 存放需代理的全部目标对象类
	 */
	public static volatile ConcurrentHashMap<String, List<ProxyBeanHandler>> clazzProxyBeanHandler = new ConcurrentHashMap<String, List<ProxyBeanHandler>>();

}
