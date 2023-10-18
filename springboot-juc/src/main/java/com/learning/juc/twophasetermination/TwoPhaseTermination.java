package com.learning.juc.twophasetermination;

import jodd.util.ThreadUtil;

/**
 * 3、两阶段终止模式应用场景
 * 两阶段终止模式适用于以下场景：
 * <p>
 * 线程终止：当需要终止一个线程时，可以使用两阶段终止模式。在准备阶段，线程可以完成一些必要的清理工作，如释放资源、关闭连接等。在终止阶段，线程可以安全地退出或终止执行。
 * <p>
 * 应用程序关闭：当需要关闭一个应用程序时，可以使用两阶段终止模式。在准备阶段，应用程序可以完成一些必要的清理工作，如保存数据、关闭文件等。在终止阶段，应用程序可以正常地退出或关闭。
 * <p>
 * 资源释放：当需要释放一些资源时，可以使用两阶段终止模式。在准备阶段，可以进行资源的释放前的准备工作，如保存状态、通知相关模块等。在终止阶段，可以安全地释放资源，确保不会造成资源泄漏或错误。
 * <p>
 * 任务取消：当需要取消一个正在执行的任务时，可以使用两阶段终止模式。在准备阶段，可以进行一些必要的清理工作，如取消请求、释放锁等。在终止阶段，可以安全地终止任务的执行，确保不会产生副作用或错误。
 * <p>
 * 总的来说，两阶段终止模式适用于需要优雅地终止线程或关闭应用程序，并确保在终止过程中完成必要的清理和资源释放操作的场景。
 * <p>
 * 这段代码实现了两阶段终止模式。在主函数中，创建了两个线程t1和t2，t1调用start方法启动一个监控线程，每隔1秒输出一条信息，t2在等待5秒后调用stop方法停止监控线程。start方法会创建一个新的线程monitorThread，该线程会在while循环中不断检查stopFlag的值，如果为false，则继续循环输出信息；如果为true，则跳出循环，输出停止信息。stop方法会将stopFlag设为true，并调用monitorThread的interrupt方法发送中断信号。
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 13:37]
 */
public class TwoPhaseTermination {

	private Thread monitorThread;

	private boolean stopFlag;

	public void start(String threadName) {
		stopFlag = false;
		monitorThread = new Thread(() -> {
			while (!stopFlag) {
				try {
					Thread.sleep(1000);
					System.out.println(threadName + "监控线程正在运行...");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println(threadName + "监控线程收到终止信号，继续完成剩余工作！");
				}
			}
			System.out.println(threadName + "监控线程已经停止.");
		});
		monitorThread.start();
	}

	public void stop(String threadName) {
		System.out.println(threadName + "发出停止信号");
		stopFlag = true;
		monitorThread.interrupt();
	}

	public static void main(String[] args) {
		TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
		Thread t1 = new Thread(() -> {
			twoPhaseTermination.start("t1");
		});

		Thread t2 = new Thread(() -> {
			ThreadUtil.sleep(5000);
			twoPhaseTermination.stop("t2线程");
		});

		t1.start();
		t2.start();

	}

}
