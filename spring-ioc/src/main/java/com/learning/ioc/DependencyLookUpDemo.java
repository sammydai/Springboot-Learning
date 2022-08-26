package com.learning.ioc;

import com.learning.ioc.annotation.Super;
import com.learning.ioc.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static java.lang.System.out;

/**
 * @Package: com.learning.ioc
 * @Description: IoC Validation
 * @Author: Sammy
 * @Date: 2021/7/22 14:56
 */

public class DependencyLookUpDemo {

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
		System.out.println("=============================");
		System.out.println("");
		//验证alias bean
		User user = beanFactory.getBean("user",User.class);
		User sammyuser = beanFactory.getBean("sammy-user", User.class);
		System.out.println("user == sammy-user " + (user == sammyuser));
		System.out.println("=============================");
		System.out.println("");
		//验证bean definition: beanDefinitionBuilder方法
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder
				.addPropertyValue("id", 1L)
				.addPropertyValue("name", "sammy")
				.getBeanDefinition();
		//bean Definition 并非最终态，可自定义修改
		//验证bean definition: AbstractBeanDefinition派生方法
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(User.class);
		MutablePropertyValues propertyvalues = new MutablePropertyValues();
		propertyvalues.addPropertyValue("id", 1L);
		propertyvalues.addPropertyValue("name", "sssssm");
		genericBeanDefinition.setPropertyValues(propertyvalues);

		lookupByType(beanFactory);
		lookupCollectionByType(beanFactory);
		lookupByAnnotationType(beanFactory);
		AbstractApplicationContext context = (AbstractApplicationContext) beanFactory;
		//添加钩子函数
		context.registerShutdownHook();
		Runtime.getRuntime().addShutdownHook(new Thread(DependencyLookUpDemo::run));
	}

	private static void lookupByAnnotationType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
			out.println("查找标注 @Super 所有的 User 集合对象：" + userMap);
		}
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			out.println("查找到的所有的 User 集合对象：" + users);
		}
	}

	private static void lookupByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		out.println("实时查找：" + user);
	}

	private static void run() {
		System.out.println("执行钩子函数");
	}


}
