package com.learning.juc.workthread;

import java.util.Random;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 14:07]
 */
public class ClientThread extends Thread {
	private final Channel channel;
	private static final Random random = new Random();

	public ClientThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}

	public void run() {
		try {
			for (int i = 0; true; i++) {
				Request request = new Request(getName(), i);
				channel.putRequest(request);
				Thread.sleep(random.nextInt(1000));
			}
		} catch (InterruptedException e) {
		}
	}
}
