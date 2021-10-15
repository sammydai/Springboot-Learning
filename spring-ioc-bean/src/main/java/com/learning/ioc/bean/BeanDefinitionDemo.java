package com.learning.ioc.bean;

import com.learning.ioc.bean.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Package: com.learning.ioc.bean
 * @Description: BeanDefinitionDemo
 * @Author: Sammy
 * @Date: 2021/7/31 20:25
 */
@Import(BeanDefinitionDemo.Config.class)
public class BeanDefinitionDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(BeanDefinitionDemo.class);

		registryBeanDefinition(context,"mecury-user",User.class);
		registryBeanDefinition(context,User.class);

		context.refresh();
		System.out.println("Config 类型的所有bean "+context.getBeansOfType(Config.class));
		System.out.println("User 类型的所有bean "+context.getBeansOfType(User.class));
		context.close();
	}

	public static void registryBeanDefinition(BeanDefinitionRegistry registry,String beanName, Class<?> clazz) {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		BeanDefinition beanDefinition = beanDefinitionBuilder
				.addPropertyValue("id",1L)
				.addPropertyValue("name","Sammy Champion")
				.getBeanDefinition();
		if (StringUtils.hasText(beanName)) {
			//注册BeanDefinition
			registry.registerBeanDefinition(beanName, beanDefinition);
		} else {
			//非命名 Bean 注册方法
			BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
		}
	}

	public static void registryBeanDefinition(BeanDefinitionRegistry registry, Class<?> clazz) {
		registryBeanDefinition(registry,null,clazz);
	}
		@Component
		public static class Config {
			@Bean(name = {"user", "sammyfight-user"})
			public User user() {
				User user = new User();
				user.setId(1L);
				user.setName("HelloSammy");
				return user;
			}
		}
	}

