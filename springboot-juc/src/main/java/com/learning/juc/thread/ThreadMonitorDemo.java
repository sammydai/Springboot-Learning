package com.learning.juc.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/13 16:03]
 */
@Slf4j
public class ThreadMonitorDemo {

	volatile boolean inited = false;

	static int threadIndex = 0;

	final BlockingQueue<String> channel = new ArrayBlockingQueue<String>(100);

	public static void main(String[] args) throws InterruptedException {
		ThreadMonitorDemo demo = new ThreadMonitorDemo();
		demo.init();
		for (int i = 0; i < 100; i++) {
			demo.service("test-" + i);
		}
		Thread.sleep(2000);
		System.exit(0);
	}

	private void service(String message) throws InterruptedException {
		channel.put(message);
	}

	private void init() {
		if (inited) {
			return;
		}
		log.info("init...");
		WorkerThread t = new WorkerThread();
		t.setName("Worker0-" + threadIndex++);
		t.setUncaughtExceptionHandler(new ThreadMonitor());
		t.start();
		inited = true;
	}

	private class ThreadMonitor implements Thread.UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			log.info("Current thread is {},is still alive{}", Thread.currentThread() == t, t.isAlive());
			String threadInfo = t.getName();
			log.info(Level.SEVERE + threadInfo + "terminated:{}", e);
			log.info("About to restart " + threadInfo);
			inited = false;
			init();
		}
	}

	private class WorkerThread extends Thread {

		@Override
		public void run() {
			log.info("Do something important....");
			String msg;
			try {
				for (; ; ) {
					msg = channel.take();
					process(msg);
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		private void process(String message) throws InterruptedException {
			log.info("process get msg:{}", message);
			if ((int) (Math.random() * 100) < 2) {
				throw new RuntimeException("test");
			}
			// Thread.sleep(1000);
			Tools.randomPause(100);
		}
	}


}

class Tools {
	public static void randomPause(int x) {
		Random random = new Random();
		int i = random.nextInt(x);
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}
}
