package com.learning.juc.thread;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.image.VolatileImage;

/**
 * @Package: com.learning.juc.thread
 * @Description: Test02
 * @Author: Sammy
 * @Date: 2021/10/30 23:44
 */

public class Test02 {
	public static void main(String[] args) {
		PrintString printString = new PrintString();
		new Thread(new Runnable() {
			@Override
			public void run() {
				printString.printStringMethod();
			}
		}).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("在 main 线程中修改打印标志");
		printString.setContinuePrint(Boolean.FALSE);
	}
	static class PrintString{
		private volatile Boolean continuePrint = true;
		// private Boolean continuePrint = true;
		public PrintString setContinuePrint(Boolean continuePrint) {
			this.continuePrint = continuePrint;
			return this;
		}

		public void printStringMethod() {
			System.out.println(Thread.currentThread().getName()+" start... ");
			while (continuePrint) {
			}
			System.out.println(Thread.currentThread().getName()+" end+++++ ");
		}
	}
}
