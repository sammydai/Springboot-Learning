package com.learning.zkconsumer.controller;

import com.learning.zkconsumer.client.HelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Package: com.learning.zkconsumer.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/21 20:33
 */

@RestController
@Slf4j
public class GreetingController {

	@Autowired
	private HelloClient helloClient;

	@RequestMapping(value = "/feign/call",method = RequestMethod.GET)
	public String greeting(@RequestParam("name") String name){
		log.info("===>zookeeper consumer msg : {}",name);
		return helloClient.HelloWorld(name);
	}
}
