package com.learning.juc.thread;

import javax.xml.transform.Templates;

/**
 * @Package: com.learning.juc.thread
 * @Description: SubThread
 * @Author: Sammy
 * @Date: 2021/10/30 22:51
 */

public class SubThread2 extends Thread {
	public SubThread2(){
		System.out.println("construct method,Thread.currentThread().getName(): " + Thread.currentThread().getName());
		System.out.println("construct method,this.getName(): " + this.getName());
	}

	@Override
	public void run() {
		System.out.println("run method,Thread.currentThread().getName(): " + Thread.currentThread().getName());
		System.out.println("run method,this.getName(): " + this.getName());
	}

	public static void main(String[] args) {
		SubThread2 t2 = new SubThread2();
		t2.setName("t2");
		t2.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread t3 = new Thread(t2);
		t3.start();
	}
}
