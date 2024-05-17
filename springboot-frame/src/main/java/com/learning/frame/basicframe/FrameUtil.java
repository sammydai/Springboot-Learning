package com.learning.frame.basicframe;

import com.learning.frame.common.SystemCaches;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 14:05]
 */
public class FrameUtil {
	public static Class<?> loadClass(String className) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?> clazz = null;
		try {
			clazz = classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		if (clazz == null && SystemCaches.classLoader != null) {
			try {
				clazz = SystemCaches.classLoader.loadClass(className);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		if (clazz == null) {
			throw new RuntimeException(className + " Loading Error! ");
		}
		return clazz;
	}
}
