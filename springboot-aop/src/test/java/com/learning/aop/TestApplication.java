package com.learning.aop;

import com.learning.aop.annotation.EnableAop;
import com.learning.aop.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 16:36]
 */
@SpringBootApplication
@EnableAop
public class TestApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	List<HelloService> helloServiceList;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (HelloService helloService : helloServiceList) {
			helloService.doSomething();
		}
	}
}
