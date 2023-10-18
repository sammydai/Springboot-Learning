package com.learning.juc.threadlocal;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 14:57]
 */
public class ClientThread extends Thread {

	public ClientThread(String name) {
		super(name);
	}


	@Override
	public void run() {
		System.out.println(" begin ");
		for (int i = 0; i < 10; i++) {
			Log.println(" main : i = " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//
			}
		}
		Log.close();
		System.out.println(" end ");
	}

	public static void main(String[] args) {
		new ClientThread("alice").start();
		new ClientThread("bobby").start();
		new ClientThread("chris").start();

	}
}
