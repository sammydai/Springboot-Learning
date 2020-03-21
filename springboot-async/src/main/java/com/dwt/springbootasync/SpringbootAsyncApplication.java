package com.dwt.springbootasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplication.class, args);
	}

}
