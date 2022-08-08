package com.learning.helloworld.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @Package: com.learning.helloworld.listener
 * @Description: ApplicationListener Test
 * @Author: Sammy
 * @Date: 2022/7/4 11:26
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		System.out.println("applicationEvent info:----------");
		System.out.println(applicationEvent);
	}
}
