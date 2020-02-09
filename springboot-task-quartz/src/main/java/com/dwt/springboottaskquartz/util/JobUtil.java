package com.dwt.springboottaskquartz.util;

import com.dwt.springboottaskquartz.job.base.BaseJob;

/**
 * @Package: com.dwt.springboottaskquartz.util
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/9 00:59
 */

public class JobUtil {
	public static BaseJob getClass(String className) throws Exception {
		Class<?> clazz = Class.forName(className);
		return (BaseJob) clazz.newInstance();
	}
}
