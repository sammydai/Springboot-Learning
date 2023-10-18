package com.learning.juc.threadlocal;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 14:55]
 */
public class Log {
	private static final ThreadLocal<TSLog> tsLogThreadLocal = new ThreadLocal<>();

	public static void println(String s) {
		getTsLog().println(s);
	}

	public static void close() {
		getTsLog().println(" ==== end of log ==== ");
		getTsLog().close();
	}

	public static TSLog getTsLog() {
		TSLog tsLog = tsLogThreadLocal.get();
		if (tsLog == null) {
			tsLog = new TSLog(Thread.currentThread().getName() + "-log.txt");
			tsLogThreadLocal.set(tsLog);
		}
		return tsLog;
	}
}
