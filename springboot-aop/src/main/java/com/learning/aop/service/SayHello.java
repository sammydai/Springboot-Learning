package com.learning.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * @Package: com.learning.aop.service
 * @Description: Say Hello
 * @Author: Sammy
 * @Date: 2020/12/15 21:35
 */
@Service
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SayHello extends SayService {
	@Override
	public void say() {
		super.say();
		log.info("hello");
	}
}
