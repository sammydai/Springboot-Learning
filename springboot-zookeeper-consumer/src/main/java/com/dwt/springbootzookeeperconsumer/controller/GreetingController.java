package com.dwt.springbootzookeeperconsumer.controller;

import com.dwt.springbootzookeeperconsumer.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.dwt.springbootzookeeperconsumer.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/21 20:33
 */

@RestController
public class GreetingController {

	@Autowired
	private HelloClient helloClient;

	@GetMapping("/clientTest")
	public String greeting(){
		return helloClient.hhhtest();
	}
}
