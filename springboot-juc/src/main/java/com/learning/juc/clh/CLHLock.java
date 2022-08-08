package com.learning.juc.clh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Package: com.learning.juc.clh
 * @Description: CLHLock
 * @Author: Sammy
 * @Date: 2022/8/6 17:25
 */

public class CLHLock implements Lock {

	private final ThreadLocal<CLHLock.Node> prev;
	private final ThreadLocal<CLHLock.Node> node;
	private final AtomicReference<CLHLock.Node> tail = new AtomicReference<>(new CLHLock.Node());

	public CLHLock() {
		this.prev = ThreadLocal.withInitial(() -> null);
		this.node = ThreadLocal.withInitial(CLHLock.Node::new);
	}

	@Override
	public void lock() {

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {

	}

	@Override
	public Condition newCondition() {
		return null;
	}

	private static class Node {
		private volatile boolean locked;
	}

}
