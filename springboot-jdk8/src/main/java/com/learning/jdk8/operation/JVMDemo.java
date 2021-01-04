package com.learning.jdk8.operation;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Package: com.learning.jdk8.operation
 * @Description: JVM Demo & Java Command Test
 * @Author: Sammy
 * @Date: 2021/1/4 09:44
 */

public class JVMDemo {
	public static void main(String[] args) throws InterruptedException {
		//启动10个线程
		IntStream.rangeClosed(1, 10).mapToObj(i -> new Thread(() -> {
			while (true) {
				//每一个线程都是一个死循环，休眠10秒，打印10M数据
				String payload = IntStream.rangeClosed(1, 10000000)
						.mapToObj(__ -> "a")
						.collect(Collectors.joining("")) + UUID.randomUUID().toString();
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(payload.length());
			}
		})).forEach(Thread::start);

		TimeUnit.HOURS.sleep(1);
	}
}
