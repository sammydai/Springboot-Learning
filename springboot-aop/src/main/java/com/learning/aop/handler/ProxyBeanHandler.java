package com.learning.aop.handler;

/**
 * 代理handler
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 14:19]
 */
public class ProxyBeanHandler {
	/**
	 * 通知类名称
	 */
	private volatile String className;
	/**
	 * 通知方法名称
	 */
	private volatile String methodName;
	/**
	 * 注解类名称
	 */
	private volatile String annotationName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getAnnotationName() {
		return annotationName;
	}

	public void setAnnotationName(String annotationName) {
		this.annotationName = annotationName;
	}
}
