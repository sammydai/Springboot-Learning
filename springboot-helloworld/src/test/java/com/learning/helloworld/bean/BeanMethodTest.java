package com.learning.helloworld.bean;

import com.learning.helloworld.config.BeanConfig;
import com.learning.helloworld.domain.People;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Package: com.learning.helloworld.bean
 * @Description: Bean Method Test
 * @Author: Sammy
 * @Date: 2020/12/8 12:33
 */

public class BeanMethodTest {
	@Test
	public void testbean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		People people = context.getBean(People.class);
		System.out.println(people);
	}
}
