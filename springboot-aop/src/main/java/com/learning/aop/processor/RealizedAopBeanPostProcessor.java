package com.learning.aop.processor;

import com.learning.aop.interceptor.CustomizedProxyInterceptor;
import com.learning.aop.util.ConfigurationUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 15:49]
 */
public class RealizedAopBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		String targetClass = bean.getClass().getName();
		Object object = bean;
		if (ConfigurationUtil.clazzProxyBeanHandler.containsKey(targetClass)) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(object.getClass());
			enhancer.setCallback(new CustomizedProxyInterceptor(ConfigurationUtil.clazzProxyBeanHandler.get(targetClass)));
			object = enhancer.create();
		}
		return object;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
