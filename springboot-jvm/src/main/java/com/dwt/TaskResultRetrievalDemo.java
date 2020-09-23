package com.dwt;

import java.io.File;
import java.util.concurrent.*;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/6/27 01:32
 */

public class TaskResultRetrievalDemo {
	final static int N_CPU = Runtime.getRuntime().availableProcessors();
	TimeUnit unit;
	BlockingQueue workQueue;
	final ThreadPoolExecutor executor = new ThreadPoolExecutor(
			0, N_CPU*2, 4, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100),new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) {
		TaskResultRetrievalDemo demo = new TaskResultRetrievalDemo();

	}

	public Future<String> recognizeImage(final String imageFile) {
		return executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return doRecognizeImage(new File(imageFile));
			}
		});
	}

	protected String doRecognizeImage(File file) {
		String result = null;
		new LinkedBlockingQueue<>();
		String[] simulateResults = {"苏Z MM518","苏Z XYZ618","苏Z 007618"};
		result = simulateResults[(int) (Math.random() * simulateResults.length)];
		return result;
	}


}
