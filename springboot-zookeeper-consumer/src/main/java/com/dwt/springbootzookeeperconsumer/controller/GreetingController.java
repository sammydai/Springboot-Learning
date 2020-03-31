package com.dwt.springbootzookeeperconsumer.controller;

import com.dwt.springbootzookeeperconsumer.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/feign/call/{name}",method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name){
		return helloClient.hhhtest(name);
	}
}
