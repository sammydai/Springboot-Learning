package com.learning.juc.workthread;

import java.util.Random;

/**
 * Request类
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 13:55]
 */
public class Request {
	private final String name; // 委托者
	private final int number;  // 请求的编号
	private static final Random random = new Random();

	public Request(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public void execute() {
		System.out.println(Thread.currentThread().getName() + " executes " + this);
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
		}
	}

	public String toString() {
		return "[ Request from " + name + " No." + number + " ]";
	}
}
