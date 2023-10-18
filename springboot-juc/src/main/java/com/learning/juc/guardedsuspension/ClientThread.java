package com.learning.juc.guardedsuspension;

import java.util.Random;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 12:07]
 */
public class ClientThread extends Thread {
	private final Random random;
	private final RequestQueue requestQueue;

	public ClientThread(RequestQueue requestQueue, String name, long seed) {
		super(name);
		this.requestQueue = requestQueue;
		this.random = new Random(seed);
	}

	public void run() {
		for (int i = 0; i < 10000; i++) {
			Request request = new Request("No." + i);
			System.out.println(Thread.currentThread().getName() + " requests " + request);
			requestQueue.putRequest(request);
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
			}
		}
	}
}
