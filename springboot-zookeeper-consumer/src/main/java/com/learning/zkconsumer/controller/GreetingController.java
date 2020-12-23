package com.learning.zkconsumer.controller;

import com.learning.zkconsumer.client.HelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
	public String greeting(@RequestParam("name") String name, HttpServletRequest request){
		log.info("===>zookeeper consumer msg : {}",name);
		return helloClient.HelloWorld(name);
	}

	@RequestMapping(value = "/feign/call/sleep", method = RequestMethod.GET)
	public String greetingsleep(@RequestParam("name") String name,HttpServletRequest request) {
		log.info("===>zookeeper consumer sleep msg : {}",name);
		return helloClient.HelloWorldSleep(name);
	}

	// @GetMapping(value = "test")
    // //@HystrixCommand(fallbackMethod = "testHystrix")
    // public String test(String name, Integer mills) {
    //     log.info("开始请求 producer，其暂停时间为：" + mills);
    //     String producerRes = restTemplate.getForObject(
    //             "http://" + service_producer_name + "/producer/hello/" + name + "?mills=" + mills, String.class);
    //     log.info("请求获取成功，开始打印请求结果：");
    //     String res = "测试consumer/test接口，基于ribbon调取服务server-producer的hello接口，返回：" + producerRes;
    //     System.out.println(res);
    //     return res;
    // }



}
