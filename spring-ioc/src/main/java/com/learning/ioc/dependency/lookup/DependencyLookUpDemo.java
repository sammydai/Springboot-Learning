package com.learning.ioc.dependency.lookup;

import com.learning.ioc.annotation.Super;
import com.learning.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Package: com.learning.ioc
 * @Description: IoC Validation
 * @Author: Sammy
 * @Date: 2021/7/22 14:56
 */

public class DependencyLookUpDemo {

	// public static void main(String[] args) {
	// 	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	// 	UserService userService = annotationConfigApplicationContext.getBean("userService", UserService.class);
	// 	userService.test();
	// }

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
		lookupInRealTime(beanFactory);
		lookupInLazy(beanFactory);
		lookupByType(beanFactory);
		lookupByCollection(beanFactory);
		lookupByAnnotation(beanFactory);
	}

	private static void lookupByAnnotation(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory annotationBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, Object> beansWithAnnotation = annotationBeanFactory.getBeansWithAnnotation(Super.class);
			System.out.println("有注解@Super的bean加载:" + beansWithAnnotation);
		}
	}

	private static void lookupByCollection(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("集合加载:" + beansOfType);
		}
	}

	private static void lookupByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		System.out.println("类型加载:" + user);
	}

	private static void lookupInLazy(BeanFactory beanFactory) {
		ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
		User user = objectFactory.getObject();
		System.out.println("延迟加载:" + user);
	}

	private static void lookupInRealTime(BeanFactory beanFactory) {
		User user = (User) beanFactory.getBean(User.class);
		System.out.println("实时加载:" + user);
	}

}
