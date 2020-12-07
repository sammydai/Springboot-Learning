package com.learning.helloworld.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.learning.helloworld.process
 * @Description: MyBeanPostProcessor
 * @Author: Sammy
 * @Date: 2020/12/6 19:49
 */
@Slf4j
@Configuration
public class MyBeanPostProcessor implements BeanPostProcessor {
	public MyBeanPostProcessor() {
		super();
		log.info("这是BeanPostProcessor实现类构造器！！");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.info("BeanPostProcessor接口方法postProcessBefore对属性进行更改,【before==》】{}",bean.getClass().getName());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.info("BeanPostProcessor接口方法postProcessAfter对属性进行更改,【after==>】{}",bean.getClass().getName());
		return bean;
	}
}
