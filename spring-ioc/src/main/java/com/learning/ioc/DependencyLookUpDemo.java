package com.learning.ioc;

import com.learning.ioc.domain.AppConfig;
import com.learning.ioc.domain.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Package: com.learning.ioc
 * @Description: IoC Validation
 * @Author: Sammy
 * @Date: 2021/7/22 14:56
 */

public class DependencyLookUpDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = annotationConfigApplicationContext.getBean("userService", UserService.class);
		userService.test();
	}

}
