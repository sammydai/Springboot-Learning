package com.learning.helloworld.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Package: com.learning.helloworld.utils
 * @Description: ApplicationContextUtils
 * @Author: Sammy
 * @Date: 2020/12/6 20:05
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware{
	private static ApplicationContext context;

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}
