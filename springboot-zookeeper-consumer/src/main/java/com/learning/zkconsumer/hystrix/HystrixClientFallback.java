package com.learning.zkconsumer.hystrix;

import com.learning.zkconsumer.client.HelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Package: com.learning.zkconsumer.hystrix
 * @Description: HystrixClientFallback
 * @Author: Sammy
 * @Date: 2020/12/21 14:57
 */
@Component
@Slf4j
public class HystrixClientFallback implements HelloClient{
	@Override
	public String HelloWorld(String name) {
		log.error("hello world fallback====> {}",name);
		return "hello world fallback";
	}

	@Override
	public String HelloWorldSleep(String name) {
		log.error("hello world fallback sleep====> {}",name);
		return "hello world sleep fallback";
	}
}
