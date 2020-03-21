package com.dwt.springbootzookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZKProvider {

	public static void main(String[] args) {
		SpringApplication.run(ZKProvider.class, args);
	}

}
