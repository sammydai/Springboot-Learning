package com.learning.kafka;

import com.learning.kafka.config.TraceContext;

import java.util.concurrent.Callable;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/7 11:14]
 */
public class TraceCallable<V> implements Callable<V> {

	private final Object context = TraceContext.getContext();

	private final Callable<V> callable;

	public TraceCallable(Callable<V> callable) {
		this.callable = callable;
	}

	@Override
	public V call() throws Exception {
		return null;
	}
}
