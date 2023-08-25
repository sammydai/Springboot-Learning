package com.learning.kafka;

import java.util.concurrent.*;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/7 11:24]
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(task);
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return super.submit(task, result);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return super.submit(task);
	}
}
