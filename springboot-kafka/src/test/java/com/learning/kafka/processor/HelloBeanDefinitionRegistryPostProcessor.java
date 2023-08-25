package com.learning.kafka.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/17 18:04]
 */
@Component
public class HelloBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
		System.out.println("HelloBeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry方法");
		System.out.println("bean定义的数据量:" + beanDefinitionRegistry.getBeanDefinitionCount());
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(HelloBeanDefinitionRegistryPostProcessor.class);
		beanDefinitionRegistry.registerBeanDefinition("helloBeanDefinitionRegistryPostProcessor", rootBeanDefinition);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		System.out.println("HelloBeanDefinitionRegistryPostProcessor的postProcessBeanFactory方法");
		System.out.println("bean定义的数据量:" + configurableListableBeanFactory.getBeanDefinitionCount());
	}
}
