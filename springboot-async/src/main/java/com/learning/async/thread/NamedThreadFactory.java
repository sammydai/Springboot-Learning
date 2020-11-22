package com.learning.async.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.learning.async.thread
 * @Description: Java Asynchronous Programming In Action C2
 * @Author: Sammy
 * @Date: 2020/11/22 12:31
 */

public class NamedThreadFactory implements ThreadFactory {
	private final AtomicInteger poolNum = new AtomicInteger(1);
	private final AtomicInteger threadNum = new AtomicInteger(1);
	private final ThreadGroup threadGroup;
	private final String namePrefix;
	private final boolean isDaemon;

	public NamedThreadFactory() {
		this("pool");
	}

	public NamedThreadFactory(String name) {
		this(name, true);
	}

	public NamedThreadFactory(String namePrefix, boolean isDaemon) {
		SecurityManager s = System.getSecurityManager();
		threadGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		this.namePrefix = namePrefix + "-" + poolNum.getAndIncrement() + "-thread-";
		this.isDaemon = isDaemon;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(threadGroup, r, namePrefix + threadNum.getAndIncrement(), 0);
		thread.setDaemon(isDaemon);
		if (thread.getPriority()!=Thread.NORM_PRIORITY) {
			thread.setPriority(Thread.NORM_PRIORITY);
		}
		return thread;
	}
}
