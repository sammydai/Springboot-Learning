package com.learning.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @Package: com.learning.helloworld.juc
 * @Description: Synchronized API Test
 * synchronized能保证原子性、变量可见性
 * @Author: Sammy
 * @Date: 2022/8/6 16:44
 */
@Slf4j
public class SynApiTest {
	public static boolean sign = false;

	public static void main(String[] args) {
		Thread Thread01 = new Thread(() -> {
		int i=0;
		while (!sign) {
			i++;
			add(i);
		}
		});
		Thread Thread02 = new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ignore) {
			}
			sign = true;
			log.info("vt.sign = true while (!sign)");
		});
		Thread01.start();
		Thread02.start();
	}

	public static synchronized int add(int i) {
		return i + 1;
	}
}
