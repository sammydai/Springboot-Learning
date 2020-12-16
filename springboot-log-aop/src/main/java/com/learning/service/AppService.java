package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Package: com.learning.service
 * @Description: IoC Container Service
 * @Author: Sammy
 * @Date: 2020/12/3 13:43
 */
@Service
public class AppService {

	/**
	 * 方法1：通过注入方式获取ApplicationContext
	 */
	@Autowired
	private ApplicationContext applicationContext;

	public void show() {
		System.out.println(applicationContext.getClass());
	}

}
