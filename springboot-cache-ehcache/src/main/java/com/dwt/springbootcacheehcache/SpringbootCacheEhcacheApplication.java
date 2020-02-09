package com.dwt.springbootcacheehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringbootCacheEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCacheEhcacheApplication.class, args);
	}

}
