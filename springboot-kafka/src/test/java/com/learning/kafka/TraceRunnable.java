package com.learning.kafka;

import com.learning.kafka.config.TraceContext;
import com.learning.kafka.util.TraceContextUtil;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/4 17:04]
 */
public class TraceRunnable implements Runnable {

	private final Object context = TraceContext.getContext();

	private final Runnable runnable;

	public TraceRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {
		Object backup = TraceContextUtil.backupAndSet(context);
		try {
			this.runnable.run();
		} finally {
			TraceContextUtil.restoreBackup(backup);
		}
	}

	public Runnable getRunnable() {
		return this.runnable;
	}

	public static TraceRunnable get(Runnable runnable) {
		if (runnable == null) {
			return null;
		} else {
			return runnable instanceof TraceRunnable ? (TraceRunnable) runnable : new TraceRunnable((runnable));
		}
	}
}
