package com.dwt.springbootzookeeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ZKProvider {

	public static void main(String[] args) {
		SpringApplication.run(ZKProvider.class, args);
		log.info("ZK Provider Start Successfully!");
	}

}
