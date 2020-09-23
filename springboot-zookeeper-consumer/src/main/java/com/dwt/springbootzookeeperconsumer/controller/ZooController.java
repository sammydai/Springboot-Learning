package com.dwt.springbootzookeeperconsumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.dwt.springbootzookeeperconsumer.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/21 21:17
 */
@Slf4j
@RestController
@RequestMapping("/zook")
public class ZooController {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Environment environment;

	@Autowired
	private CuratorFramework curatorFramework;

	public String getZook() {
		return "";
	}

	@RequestMapping("/getServices")
	public String discoveryClient() {
		List<String> serviceList = client.getServices();
		log.info("注册服务的数量>>>>>>>>>>>>>>>>>" + serviceList.size());
		for (String service : serviceList) {
			log.info("注册的服务>>>>>>" + service);
		}
		return "info";
	}

	/**
	 * @description
	 * @param serviceId:  服务注册在注册中心的名字，server.name这个参数值
	 * @return java.lang.String
	 * @Exception
	 *
	 */


	@RequestMapping("/getServices/{serviceId}")
	public String discoverClient(@PathVariable("serviceId") String serviceId){
		List<ServiceInstance> instances = client.getInstances(serviceId);
		 StringBuffer sb = new StringBuffer();
        if (instances != null && instances.size() > 0 ) {
            sb.append(instances.get(0).getUri()+",");
        }

        return "hello world  "+sb.toString();
	}

	@RequestMapping("/env")
	public String test() {
		String[] profiles = environment.getActiveProfiles();
		log.info("profiles>>>>>>>" + profiles.length);
		for (String item : profiles) {
			log.info("item>>>>>>>>>>>>>>>" + item);
		}
		String name = environment.getProperty("url");
		System.out.println("name: " + name);
		return "Hello," + name;
	}


}
