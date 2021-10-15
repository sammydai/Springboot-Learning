package com.learning.actuator.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @Package: com.learning.actuator.bean
 * @Description:
 * @Author: Sammy
 * @Date: 2021/7/31 17:58
 */

public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		System.out.println("[BeanFactoryPostProcessor]");
	}
}
