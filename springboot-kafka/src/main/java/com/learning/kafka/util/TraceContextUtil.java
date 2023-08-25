package com.learning.kafka.util;

import com.learning.kafka.config.TraceContext;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/4 17:13]
 */
public class TraceContextUtil {

	public static Object backupAndSet(Object currentContext) {
		Object backupContext = TraceContext.getContext();
		TraceContext.setContext(currentContext);
		return backupContext;
	}

	public static void restoreBackup(Object backup) {
		TraceContext.setContext(backup);
	}
}
