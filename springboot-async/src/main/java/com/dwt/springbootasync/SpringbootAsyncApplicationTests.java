package com.dwt.springbootasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootAsyncApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplicationTests.class, args);
	}

}
