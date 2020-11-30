package com.learning.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * @Package: com.learning.juc.utils;
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/27 12:01
 */

public class CompleteFutureTest {
	public static void main(String[] args) {
		CompletableFuture<String> f0 = CompletableFuture.supplyAsync(() -> "WORldd").thenApply(s -> s + "QQ").thenApply(String::toUpperCase);
		System.out.println(f0.join());
	}

	 private static void sleep(int time, TimeUnit unit) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public void makeTeaTest(){
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			System.out.println("T1:洗⽔壶...");
			sleep(1, TimeUnit.SECONDS);//模拟耗时

			System.out.println("T1:烧开⽔...");
			sleep(2, TimeUnit.SECONDS);//模拟耗时
		});

		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("T2:洗茶壶...");
			sleep(1, TimeUnit.SECONDS);

			System.out.println("T2:洗茶杯...");
			sleep(2, TimeUnit.SECONDS);

			System.out.println("T2:拿茶叶...");
			sleep(1, TimeUnit.SECONDS);
			return "柠檬茶lemon";
		});

		CompletableFuture<String> f3 = f1.thenCombine(f2, (Void __, String tf) -> {
			System.out.println("T1:拿到茶叶:" + tf);
			System.out.println("T1:泡茶...");
			return "上茶:" + tf;
		});

		System.out.println(f3.join());
	}
}




