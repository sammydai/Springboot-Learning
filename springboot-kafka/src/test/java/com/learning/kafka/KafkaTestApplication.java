package com.learning.kafka;

import com.learning.kafka.config.MyConfig;
import com.learning.kafka.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/14 21:33]
 */
public class KafkaTestApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
		// User user = (User) annotationConfigApplicationContext.getBean("user");
		// user.sayHello();
		User userfrombf = (User) annotationConfigApplicationContext.getBean("userfrombf");
		BeanDefinitionRegistryPostProcessor helloBeanDefinitionRegistryPostProcessor = (BeanDefinitionRegistryPostProcessor) annotationConfigApplicationContext.getBean("helloBeanDefinitionRegistryPostProcessor");
		System.out.println(userfrombf);
		System.out.println(helloBeanDefinitionRegistryPostProcessor.getClass().getCanonicalName());
	}
}
