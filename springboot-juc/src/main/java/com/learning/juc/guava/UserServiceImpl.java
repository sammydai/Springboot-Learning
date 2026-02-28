package com.learning.juc.guava;

import org.apache.catalina.User;

import java.util.concurrent.TimeUnit;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/7/12 11:40]
 */
public class UserServiceImpl implements UserService {
	@Override
	public String getName() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(2000);
		return "zhang san";
	}
}
