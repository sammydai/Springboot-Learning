package com.dwt;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/21 22:01
 */

public class FuterTestMain {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// Future<Integer> result = service.submit(new FutureTest());
		FutureTask<Integer> futureTask = new FutureTask<>(new FutureTest());
		service.submit(futureTask);
		service.shutdown();
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

		try {
            if(futureTask.get()!=null){
                System.out.println("task运行结果"+futureTask.get());
            }else{
                System.out.println("未获取到结果");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
	}
}
