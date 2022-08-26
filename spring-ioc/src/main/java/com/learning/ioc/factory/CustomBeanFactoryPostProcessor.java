package com.learning.ioc.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.*;

import java.util.Iterator;

/**
 * @Package: com.learning.ioc.factory
 * @Description: CustomBeanFactoryPostProcessor
 * @Author: Sammy
 * @Date: 2021/7/22 16:52
 */

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor,BeanPostProcessor,InstantiationAwareBeanPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("调用了自定义的BeanFactoryPostProcessor " + beanFactory);
		Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
		String[] names = beanFactory.getBeanDefinitionNames();
		for (int i = 0; i < names.length; i++) {
			// String name = names[i];
			String name = names[i];
			BeanDefinition bd = beanFactory.getBeanDefinition(name);
			System.out.println("");
			System.out.println(name + " bean property " + bd.getPropertyValues().toString());
			System.out.println("===结束===");
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println();
		System.out.println(String.format("Bean初始化前,bean:%s,beanName:%s", bean.toString(), beanName));
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println();
		System.out.println(String.format("Bean初始化后,bean:%s,beanName:%s", bean.toString(), beanName));
		return bean;
	}

}
