package com.learning.actuator.bean;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @Package: com.learning.actuator.bean
 * @Description: TestApplicationContextInitializer
 * @Author: Sammy
 * @Date: 2021/7/31 16:17
 */

/**
 * ApplicationContextInitializer支持Order注解，表示执行顺序，越小越早执行；
 */
@Order(11)
public class TestApplicationContextInitializer implements ApplicationContextInitializer {
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.out.println("[ApplicationContextInitializer]");
		// 打印容器里面有多少个bean
        System.out.println("bean count=====" + applicationContext.getBeanDefinitionCount());

        // 打印人所有 beanName
        System.out.println(applicationContext.getBeanDefinitionCount() + "个Bean的名字如下：");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

		System.out.println("===============================");
	}
}
