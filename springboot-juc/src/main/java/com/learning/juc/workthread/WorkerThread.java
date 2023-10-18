package com.learning.juc.workthread;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 13:57]
 */
public class WorkerThread extends Thread {

	private final Channel channel;

	public WorkerThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true) {
			Request request = channel.takeRequest();
			request.execute();
		}
	}
}
