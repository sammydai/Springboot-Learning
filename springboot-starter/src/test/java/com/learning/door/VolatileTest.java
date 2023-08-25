package com.learning.door;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/4 15:33]
 */
public class VolatileTest {
	private String value = null;

	private volatile boolean hasNewValue = false;

	private volatile int count = 0;

	public void inc() {
		count++;
	}

	public void dec() {
		count--;
	}

	public int getCount() {
		return count;
	}


	public void put(String value) {
		while (hasNewValue) {
			// System.out.println("等待1");
		}
		this.value = value;
		hasNewValue = true;
	}

	public String get() {
		while (!hasNewValue) {
			// System.out.println("等待2");
		}
		String value = this.value;
		hasNewValue = false;
		return value;
	}


	public static void main(String[] args) {
		VolatileTest obj = new VolatileTest();

		new Thread(() -> {
			while (true) {
				obj.put("time：" + System.currentTimeMillis());
			}
		}).start();
		new Thread(() -> {
			while (true) {
				System.out.println(obj.get());
			}
		}).start();
	}

	public static void method(String[] args) throws InterruptedException {
		while (true) {
			VolatileTest volatileTest = new VolatileTest();

			Thread thread1 = new Thread(() -> {

				for (int i = 0; i < 50; i++) {
					volatileTest.inc();
				}


			});
			Thread thread2 = new Thread(() -> {
				for (int i = 0; i < 50; i++) {
					volatileTest.dec();
				}
			});
			thread1.start();
			thread2.start();
			thread1.join();
			thread2.join();
			int count1 = volatileTest.getCount();
			if (count1 != 0) {
				System.out.println("counter = " + count1);
			}
		}
	}
}
