package com.learning.juc.clh;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Package: com.learning.juc.clh
 * @Description: SyncLock
 * @Author: Sammy
 * @Date: 2022/8/6 17:29
 */

public class SyncLock {

	private final Sync sync;

	public SyncLock() {
		sync = new Sync();
	}

	public void lock() {
		sync.acquire(1);
	}

	public void unlock() {
		sync.release(1);
	}

	private static class Sync extends AbstractQueuedSynchronizer{
		/**
		 * 以预期值为 0，写 入更新值 1，写入成功则获取锁成功。
		 * 其实这个过程就是对 state 使用 unsafe 本地方法，
		 * 传递偏移量 stateOffset 等参数，进行值交换操作。
		 * @param arg the acquire argument. This value is always the one
		 *        passed to an acquire method, or is the value saved on entry
		 *        to a condition wait.  The value is otherwise uninterpreted
		 *        and can represent anything you like.
		 * @return
		 */
		@Override
		protected boolean tryAcquire(int arg) {
			return compareAndSetState(0, 1);
		}

		@Override
		protected boolean tryRelease(int arg) {
			setState(0);
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
	}
}
