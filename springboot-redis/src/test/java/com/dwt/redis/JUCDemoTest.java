package com.dwt.redis;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/2/10 10:20]
 */

public class JUCDemoTest {

	public static void test(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		List<Integer> list = new ArrayList<>();
		Thread threadA = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 在执行..");
			try {
				Thread.sleep(1000);
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		},"thread-A");
		Thread threadB = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 在执行..");
			try {
				Thread.sleep(1000);
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		},"thread-B");
		Thread threadC = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 在执行..");
			try {
				Thread.sleep(1000);
				countDownLatch.countDown();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		},"thread-C");
		Thread threadD = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 等待其他线程执行完毕");
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println(Thread.currentThread().getName() + " 执行自己的线程工作");
		},"thread-D");

		threadD.start();
		threadA.start();
		threadB.start();
		threadC.start();
	}


		public static void main(String[] args) {
			// 创建屏障，等待3个线程
			CyclicBarrier barrier = new CyclicBarrier(3, () -> {
				System.out.println(Thread.currentThread().getName() +
						" 执行屏障任务，所有线程准备就绪");
			});

			// 创建线程池
			ExecutorService executor = Executors.newFixedThreadPool(3);

			for (int i = 0; i < 3; i++) {
				final int taskId = i;
				executor.submit(() -> {
					try {
						// 第一阶段任务
						System.out.println("任务" + taskId + " 开始第一阶段处理");
						Thread.sleep(1000 + taskId * 500);
						System.out.println("任务" + taskId + " 第一阶段完成");

						// 等待其他线程
						int arrivalIndex = barrier.await();
						System.out.println("任务" + taskId +
								" 通过屏障，到达顺序：" + arrivalIndex);

						// 第二阶段任务
						System.out.println("任务" + taskId + " 开始第二阶段处理");
						Thread.sleep(500);
						System.out.println("任务" + taskId + " 第二阶段完成");

						// 可以重复使用屏障
						barrier.await();
						System.out.println("任务" + taskId + " 所有阶段完成");

					} catch (BrokenBarrierException e) {
						throw new RuntimeException(e);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			}

			executor.shutdown();
		}
	}

