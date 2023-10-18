package com.learning.kafka.interceptor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NameThreadFactory
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/10/18 16:27]
 */
public class NameThreadFactory implements ThreadFactory {
	private final String namePrefix;

	private final AtomicInteger threadNumber = new AtomicInteger(1);

	public NameThreadFactory(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, namePrefix + " - " + threadNumber.getAndIncrement());
		return thread;
	}
}
