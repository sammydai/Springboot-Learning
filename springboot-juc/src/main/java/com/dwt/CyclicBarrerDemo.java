package com.dwt;

import java.util.concurrent.*;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/25 14:49
 */

public class CyclicBarrerDemo {
	private static int SIZE = 5;
	private static CyclicBarrier cyclicBarrier;

	public static void main(String[] args) {
		cyclicBarrier = new CyclicBarrier(SIZE,()->{
			System.out.println("CyclicBarrier's parties is: "+cyclicBarrier.getParties());
		});
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 5; i++) {
			service.submit(()->{
				try {
					System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
					cyclicBarrier.await();
					System.out.println(Thread.currentThread().getName() + " continued.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}

			});
		}
		service.shutdown();
	}

}
