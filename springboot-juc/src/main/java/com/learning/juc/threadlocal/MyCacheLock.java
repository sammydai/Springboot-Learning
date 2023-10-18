package com.learning.juc.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 14:37]
 */
public class MyCacheLock {
	private final Map<String, Object> map = new HashMap<>();

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void put(String key, Object value) {
		ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "写入" + key);
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "写入完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}

	public void get(String key) {
		ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
		readLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "读取" + key);
			map.get(key);
			System.out.println(Thread.currentThread().getName() + "读取完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}

	public static void main(String[] args) {
		MyCacheLock myCacheLock = new MyCacheLock();
		//十个写线程
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			new Thread(() -> {
				myCacheLock.put(temp + "", temp + "");
			}, String.valueOf(i)).start();
		}
		//十个读线程
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			new Thread(() -> {
				myCacheLock.get(temp + "");
			}, String.valueOf(i)).start();
		}
	}
}
