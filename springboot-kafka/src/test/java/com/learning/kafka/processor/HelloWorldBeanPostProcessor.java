package com.learning.kafka.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/15 09:46]
 */
@Component
public class HelloWorldBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization: " + bean + ", " + beanName + ", " + applicationContext.getApplicationName());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization: " + bean + ", " + beanName + ", " + applicationContext.getApplicationName());
		return bean;
		// return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
		// 	@Override
		// 	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 		System.out.println("BeanPostProcessor织入，Spring AOP实现原理");
		// 		return method.invoke(proxy, args);
		// 	}
		// });
	}
}
