package com.learning.kafka.config;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/4 15:13]
 */
public class TraceContext {

	private static final ThreadLocal<Object> CONTEXT = new ThreadLocal<>();

	public static Object getContext() {
		return CONTEXT.get();
	}

	public static void setContext(Object obj) {
		CONTEXT.set(obj);
	}

	public static void removeContext(Object obj) {
		CONTEXT.remove();
	}
}
