package com.learning.actuator.health;

import java.util.concurrent.*;
import jodd.util.concurrent.ThreadFactoryBuilder;
/**
 * @Package: com.learning.actuator.health
 * @Description: ThreadPoolProvider
 * @Author: Sammy
 * @Date: 2020/12/22 17:50
 */

public class ThreadPoolProvider {

	private static ThreadPoolExecutor demoThreadPool = new ThreadPoolExecutor(
			1, 1, 2, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(10),
			new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get());

	private static ThreadPoolExecutor ioThreadPool = new ThreadPoolExecutor(
			50, 50, 2, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(100),
			new ThreadFactoryBuilder().setNameFormat("io-threadpool-%d").get());

	public static ThreadPoolExecutor getDemoThreadPool() {
		return demoThreadPool;
	}

	public static ThreadPoolExecutor getIoThreadPool() {
		return ioThreadPool;
	}

}
