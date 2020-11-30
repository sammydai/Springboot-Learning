package com.learning.juc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.learning.juc.domain
 * @Description: Item
 * @Author: Sammy
 * @Date: 2020/11/28 16:17
 */
@Data
@RequiredArgsConstructor
public class Item {
	final String name; //商品名
	int remaining = 1000; //库存剩余
	@ToString.Exclude //ToString不包含这个字段
	ReentrantLock lock = new ReentrantLock();

	public String getName() {
		return name;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}
}

