package com.learning.juc.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Package: com.learning.juc.future
 * @Description: Completable Future Stage Test Set
 * @Author: Sammy
 * @Date: 2020/12/4 15:37
 */

public class CompletableStageSet {

	public static CompletableFuture<String> doSomethingOne(String encodedCompanyId) {
		return CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String id = encodedCompanyId;
			return id;
		});
	}

	public static CompletableFuture<String> doSomethingTwo(String companyId) {
		return CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String str = companyId + " :alibaba";
			return str;
		});
	}

	@Test
	public void testCompose() throws ExecutionException, InterruptedException {
		CompletableFuture result = doSomethingOne("123").thenCompose((String id) -> {return doSomethingTwo(id);});
		System.out.println("ss1");
		System.out.println(result.get());
		System.out.println("ss2");
	}

	@Test
	public void testCombine() throws ExecutionException, InterruptedException {
		CompletableFuture result = doSomethingOne("123").thenCombine(doSomethingTwo("456"),(one,two)-> "result" + one + " , " + two);
		System.out.println(result.get());
	}
}
