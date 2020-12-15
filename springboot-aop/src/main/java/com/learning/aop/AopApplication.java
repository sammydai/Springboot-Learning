package com.learning.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @Package: com.learning.aop
 * @Description: Spring Aop Ioc Learning
 * @Author: Sammy
 * @Date: 2020/12/15 15:08
 */
@SpringBootApplication
@Slf4j
public class AopApplication {
	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	@Autowired
	private StandardEnvironment environment;

	@PostConstruct
	public void init() {
		Arrays.asList("user.name","management.server.port").forEach(key->{
			environment.getPropertySources().forEach(propertySource -> {
				if (propertySource.containsProperty(key)) {
					log.info("{}->{}实际取值:{}", propertySource, propertySource.getProperty(key), environment.getProperty(key));
				}
			});
		});
		System.out.println("配置优先级: ");
		environment.getPropertySources().stream().forEach(System.out::println);
	}
}


