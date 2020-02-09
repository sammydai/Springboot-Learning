package com.dwt.springboottaskquartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.dwt.springboottaskquartz.mapper"})
@SpringBootApplication
public class SpringbootTaskQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTaskQuartzApplication.class, args);
	}

}
