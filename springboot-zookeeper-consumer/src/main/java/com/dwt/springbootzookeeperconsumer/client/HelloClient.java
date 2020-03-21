package com.dwt.springbootzookeeperconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Package: com.dwt.springbootzookeeperconsumer.client
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/21 20:19
 */
@FeignClient("HelloWorldProvider")
public interface HelloClient {

	@GetMapping("/helloworld")
	public String hhhtest();
}
