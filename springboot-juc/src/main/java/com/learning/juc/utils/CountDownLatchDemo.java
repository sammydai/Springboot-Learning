package com.learning.juc.utils;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Package: com.learning.juc.utils;
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/25 12:14
 */

/**
 * 多个线程等待一个线程
 */
public class CountDownLatchDemo {
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	private static CountDownLatch countDownLatch = new CountDownLatch(5);

	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("所有运动员都到位了，比赛开始！"));
	private static Integer count = 0;

	public static void main(String[] args) throws Exception {
		// new CountDownLatchDemo().waitMultiThread();
		// new CountDownLatchDemo().waitOneThread();
		// new CountDownLatchDemo().waitOneThread1();
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Athlete(countDownLatch,cyclicBarrier,i));
		}
		countDownLatch.await();
		System.out.println("下一个环节"+countDownLatch.getCount());
	}

	public void waitOneThread(){
		ExecutorService service = Executors.newFixedThreadPool(7);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		for (int i = 1; i < 8; i++) {
			int j = i;
			service.submit(() -> {
				try {
					countDownLatch.await();
					Thread.sleep((long) new Random().nextInt(1) + 1);
					System.out.println("运动员" + j + "起跑");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDownLatch.countDown();
		System.out.println("裁判发出枪响，比赛开始……");
        service.shutdown();
	}

	public void waitMultiThread() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(7);
		CountDownLatch countDownLatch = new CountDownLatch(7);
		for (int i = 1; i < 8; i++) {
			int j = i;
			service.submit(() -> {
				try {
					Thread.sleep((long) new Random().nextInt(1) + 1);
					System.out.println("运动员" + j + "到终点");
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDownLatch.await();
		System.out.println("所有运动员都到终点，裁判开始算成绩...");
        service.shutdown();
	}

	public void waitOneThread1() {
		ExecutorService service = Executors.newFixedThreadPool(7);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		for (int i = 1; i < 8; i++) {
			int j = i;
			service.submit(()->{
				try {
					countDownLatch.await();
					Thread.sleep((long) new Random().nextInt(1) + 1);
					System.out.println("运动员" + j + "听到发令枪，开始比赛！");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDownLatch.countDown();
		System.out.println("裁判员打开发令枪，宣布开始比赛");
		service.shutdown();
	}

}

class Athlete implements Runnable{
	private final CountDownLatch countDownLatch;
    private final CyclicBarrier cyclicBarrier;
    private final int id;

	public Athlete(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, int id) {
		this.countDownLatch = countDownLatch;
		this.cyclicBarrier = cyclicBarrier;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.println(id+"热身活动，准备开始-----------");
			cyclicBarrier.await();
			System.out.println(id+"跑步中--------");
            System.out.println(id+"跑完了--------");
		} catch (Exception e) {
			System.out.println(id+"受伤啦！");
		}finally {
			countDownLatch.countDown();
		}

	}
}