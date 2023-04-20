package com.learning.juc.auto;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Package: com.learning.juc.auto
 * @Description: AtomicReferenceExample1
 * @Author: Sammy
 * @Date: 2022/9/5 16:57
 */

public class AtomicReferenceExample1 {

	static volatile DebitCard debitCard = new DebitCard("zhangsan", 0);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				final DebitCard dc = debitCard;
				DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
				System.out.println(newDC);
				debitCard = newDC;
				try {
					TimeUnit.MILLISECONDS.sleep(current().nextInt(20));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
