package com.learning.juc.workthread;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 14:07]
 */
public class Main {
	public static void main(String[] args) {
		Channel channel = new Channel(5);   // 工人线程的个数
		channel.startWorkers();
		new ClientThread("Alice", channel).start();
		new ClientThread("Bobby", channel).start();
		new ClientThread("Chris", channel).start();
	}
}
