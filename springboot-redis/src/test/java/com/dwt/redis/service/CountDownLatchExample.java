package com.dwt.redis.service;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch cdl = new CountDownLatch(1);

        int concurrency = 100;

        final CountDownLatch cdln = new CountDownLatch(concurrency);

        final Random random = new Random();
        for (int i = 0; i < concurrency; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(10_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "准备就绪");
                // 让并发线程都等待发出信号
                cdln.countDown();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始工作");
            }).start();
        }
        cdln.await();
        System.out.println("******************** 发出开始信号***********");
        cdl.countDown();
    }
}