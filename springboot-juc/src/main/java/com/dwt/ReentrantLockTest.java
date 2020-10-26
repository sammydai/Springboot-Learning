package com.dwt;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/25 13:42
 */

public class ReentrantLockTest implements Runnable{
	public static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		// accessReentran();
		// ReentrantLockTest interceptLockTest = new ReentrantLockTest();
		// Thread t1 = new Thread(interceptLockTest);
		// Thread t2 = new Thread(interceptLockTest);
		// t1.start();
		// t2.start();
		// Thread.sleep(2000);
		// t1.interrupt();
	}

	public static void accessReentran(){
		lock.lock();
		try {
			System.out.println("被递归调用了");
			if (lock.getHoldCount()<5) {
				accessReentran();
				System.out.println(lock.getHoldCount());
			}
		}finally {
			// System.out.println("finally---"+lock.getHoldCount());
			lock.unlock();
		}
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "成功获取到了锁");
                Thread.sleep(100000);
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "业务方法执行期间异常");
            } finally {
                lock.unlock();
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + "等待获取锁时候被中断异常");
        }

	}
}
