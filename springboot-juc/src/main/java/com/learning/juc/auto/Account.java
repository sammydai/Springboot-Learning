package com.learning.juc.auto;

/**
 * @Package: com.learning.juc.auto
 * @Description: Account Lock
 * @Author: Sammy
 * @Date: 2022/9/23 14:25
 */

public class Account {
	private int balance;

	private Object lock;

	/**
	 * 我们把 Account 默认构造函数变为 private，
	 * 同时增加一个带 Object lock 参数的构造函数，
	 * 创建 Account 对象时，传入相同的 lock，
	 * 这样所有的 Account 对象都会共享这个 lock 了。
	 */
	private Account() {
	}

	public Account(Object lock) {
		this.lock = lock;
	}

	// 转账
	void transfer(Account target, int amt) {
		// 此处检查所有对象共享的锁
		synchronized (lock) {
			if (this.balance > amt) {
				this.balance -= amt;
				target.balance += amt;
			}
		}
	}
}
