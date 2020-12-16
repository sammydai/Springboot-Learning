package com.learning.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Package: com.learning.service
 * @Description: IoC Container Service
 * @Author: Sammy
 * @Date: 2020/12/3 13:54
 */
@Component
public class AppService2 implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 方法2：spring 在bean 初始化后会判断是不是ApplicationContextAware的子类，
	 * 调用setApplicationContext（）方法，
	 * 会将容器中ApplicationContext传入进去
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public Object getBeanByName(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	// public <T> getBeanByClazz(Class<T> clazz) {
	// 	return getApplicationContext().getBeansOfType(clazz);
	// }

}
