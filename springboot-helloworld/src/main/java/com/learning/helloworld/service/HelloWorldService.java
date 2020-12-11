package com.learning.helloworld.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.learning.helloworld.service
 * @Description: Hello World Service
 * @Author: Sammy
 * @Date: 2020/12/8 10:04
 */

public class HelloWorldService {
	public String sayHello() {
		return "Hello World";
	}
}


