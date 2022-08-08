package com.learning.juc.clh;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Package: com.learning.juc.clh
 * @Description: SyncLockTest
 * @Author: Sammy
 * @Date: 2022/8/6 17:42
 */

public class SyncLockTest {

	@Test
	public void test_SyncLock() throws InterruptedException {
		final SyncLock lock = new SyncLock();
		for (int i=0;i<10;i++){
			Thread.sleep(200);
			new Thread(new TestLock(lock), String.valueOf(i)).start();
		}
		Thread.sleep(100000);
	}

	// @Test
	// public void test_stateOffset() throws Exception {
	// 	Unsafe unsafe = getUnsafeInstance();
	// 	long state = unsafe.objectFieldOffset
	// 			(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
	// 	System.out.println(state);
	// }

	private class TestLock implements Runnable {
		private SyncLock lock;
		public TestLock(SyncLock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				lock.lock();
				Thread.sleep(1000);
				System.out.println(String.format("Thread %s Completed", Thread.currentThread().getName()));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
