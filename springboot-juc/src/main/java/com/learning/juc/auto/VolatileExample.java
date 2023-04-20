package com.learning.juc.auto;

/**
 * @Package: com.learning.juc.auto
 * @Description: 用两把锁，取款和修改密码是可以并行的。
 * 用不同的锁对受保护资源进行精细化管理，能够提升性能。
 * 这种锁还有个名字，叫细粒度锁。
 * @Author: Sammy
 * @Date: 2022/9/23 13:46
 */

public class VolatileExample {
	private final Object balLock = new Object();

	private Integer balance;

	private final Object pwLock = new Object();

	private String password;

	void withdram(Integer amt) {
		synchronized (balance) {
			if (this.balance > amt) {
				this.balance -= amt;
			}
		}
	}

	Integer getBalance() {
		synchronized (balLock) {
			return balance;
		}
	}

	void updatePassword(String pw) {
		synchronized (pwLock) {
			this.password = pw;
		}
	}

	String getPassword() {
		synchronized (pwLock) {
			return password;
		}
	}
}
