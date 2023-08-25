package com.learning.kafka.config;

import com.learning.kafka.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/14 21:36]
 */
@Configuration
@PropertySource("classpath:beanName.properties")
@ComponentScan({"com.learning.kafka.processor"})

public class MyConfig {

	@Value("${bean.name.controller}")
	String testvalue;

	@Value("${bean.name.id}")
	Long id;

	@Bean
	public User user() {
		System.out.println("user bean initializer..." + testvalue);
		return new User(id, testvalue);
	}
}
