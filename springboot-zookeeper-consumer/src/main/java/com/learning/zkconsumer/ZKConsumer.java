package com.learning.zkconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class ZKConsumer {

	public static void main(String[] args) {
		SpringApplication.run(ZKConsumer.class, args);
		log.info("ZK Consumer Start Successfully!");
	}

}
