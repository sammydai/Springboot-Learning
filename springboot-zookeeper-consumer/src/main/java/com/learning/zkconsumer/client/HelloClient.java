package com.learning.zkconsumer.client;

import com.learning.zkconsumer.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Package: com.learning.zkconsumer.client
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/21 20:19
 */
/**
 * @description 通过引入spring-cloud-starter-openfeign组件包使用声明式服务调用方式调用远程服务，使用@FeignClient（“service-name”）注解一个接口并将它自动连接到我们的应用程序中，以便我们以编程方式访问此服务。
 * @Exception
 *
 */

/**
 * @description  FeignClient调用HelloWorldProvider 提供者注册在zk的服务名，调用HelloWorldProvider的服务 /helloworld
 * @Exception
 *
 */

@FeignClient(value = "HelloWorldZKProvider",configuration = FeignClientConfig.class)
public interface HelloClient {

	@GetMapping("/zk/provider")
	public String HelloWorld(@RequestParam("name") String name);

	@GetMapping("/zk/provider/sleep")
	public String HelloWorldSleep(@RequestParam("name") String name);

}
