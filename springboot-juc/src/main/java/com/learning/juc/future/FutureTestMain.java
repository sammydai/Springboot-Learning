package com.learning.juc.future;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.*;

/**
 * @Package: com.learning.juc.utils
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/21 22:01
 */
@Slf4j
public class FutureTestMain {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<String> sft2 = new FutureTask<>(new T2Task());
		// FutureTask<String> sft1 = new FutureTask<>(new T1Task(sft2));
		ExecutorService service = Executors.newFixedThreadPool(2);
		log.info("main thread start working....");
		ListeningExecutorService lPool = MoreExecutors.listeningDecorator(service);
		ListenableFuture<String> hFuture = lPool.submit(new T2Task());
		Futures.addCallback(hFuture, new FutureCallback<String>() {
			@Override
			public void onSuccess(@Nullable String s) {
				log.info("this is success: {}",s);
			}

			@Override
			public void onFailure(Throwable throwable) {
				log.info("have no tea");
			}
		}, lPool);
		log.info("main thread finish working!!");

		// service.submit(sft1);
		// service.submit(sft2);
		// System.out.println(sft1.get());
	}

	public static void methodA() throws ExecutionException, InterruptedException {
		FutureTask<String> sft3 = new FutureTask<>(() -> {
			log.info("do 1st thing");
			log.info("do 2nd thing");
			Thread.sleep(3000);
			log.info("do 3rd thing");
			return "all things done";
		});
		Thread aa = new Thread(sft3, "Thread-3");
		aa.start();
		log.info("start get future task result....");
		String s = sft3.get();
		log.info(s);
		log.info("finish get future task result");
	}
}

class Task implements Runnable{

	Result result;

	public Task(Result result) {
		this.result = result;
	}

	@Override
	public void run() {
		String msg = result.getMsg();
		result.setCode("999");
	}
}

class Result{
	public Result() {
	}

	public Result(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	String code;
	String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

class T1Task implements Callable<String>{

	private FutureTask<String> ft2;

	public T1Task(FutureTask<String> ft2) {
		this.ft2 = ft2;
	}

	@Override
	public String call() throws Exception {

		 System.out.println("T1:洗水壶...");
		 TimeUnit.SECONDS.sleep(1);

		System.out.println("T1:烧开水...");
		TimeUnit.SECONDS.sleep(5);

		String s = ft2.get();
		System.out.println("T1:拿到茶叶:"+s);

		System.out.println("T1:泡茶...");
    	return "上茶:" + s;
	}
}

class T2Task implements Callable<String>{
	public T2Task() {
	}

	@Override
	public String call() throws Exception {
		System.out.println("T2:洗茶壶...");
		TimeUnit.SECONDS.sleep(1);

		System.out.println("T2:洗茶杯...");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("T2:拿茶叶...");
		TimeUnit.SECONDS.sleep(1);
		return "红茶black tea";
	}
}