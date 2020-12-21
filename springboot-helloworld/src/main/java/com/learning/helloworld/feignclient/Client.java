package com.learning.helloworld.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client")
public interface Client {
	@GetMapping("/feignaop/server")
	String api();
}
