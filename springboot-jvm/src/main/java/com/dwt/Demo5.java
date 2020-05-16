package com.dwt;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/5 19:46
 */

public class Demo5 {
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人"));
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				try {
					barrier.await();
					// System.out.println("barrier"+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			},"thread-"+i).start();

		}

	}


}

