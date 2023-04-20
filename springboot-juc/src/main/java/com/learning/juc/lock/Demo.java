package com.learning.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.learning.juc.lock
 * @Description: Demo2
 * @Author: Sammy
 * @Date: 2022/9/23 16:05
 */

public class Demo {
	public static void main(String[] args) {
		ReentrantLock r1 = new ReentrantLock();
		Condition numberCondition = r1.newCondition();
		Condition characterCondition = r1.newCondition();
		new Thread(() -> {
			try {
				r1.lock();
				for (int i = 1; i <= 52; i++) {
					System.out.println(i + " ");
					if (i % 2 == 0) {
						characterCondition.signalAll();
						numberCondition.await();
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				r1.unlock();
			}
		}).start();

		new Thread(() -> {
			try {
				r1.lock();
				for (int i = 0; i < 26; i++) {
					System.out.println((char) ('A' + i) + " ");
					numberCondition.signalAll();
					if (i != 25) {
						characterCondition.await();
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				r1.unlock();
			}
		}).start();
	}
}
