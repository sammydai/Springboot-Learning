// package com.learning.kafka;
//
// import org.junit.Test;
//
//
// import java.util.Date;
// import java.util.Timer;
// import java.util.TimerTask;
//
// /**
//  * [一句话描述该类的功能]
//  *
//  * @author : [Sammy]
//  * @version : [v1.0]
//  * @createTime : [2023/8/11 10:47]
//  */
// public class TimerTest {
//
// 	@Test
// 	public void givenUsingTimer_whenSchedulingTaskOnce_thenCorrect() throws InterruptedException {
// 		TimerTask task = new TimerTask() {
// 			@Override
// 			public void run() {
// 				System.out.println("Task performed on: " + new Date() + "n" + "Thread's name:" + Thread.currentThread().getName());
// 			}
// 		};
// 		Timer timer = new Timer("Timer");
// 		long delay = 1000L;
// 		timer.schedule(task,delay);
// 		Thread.sleep(delay*2);
// 	}
//
// }
