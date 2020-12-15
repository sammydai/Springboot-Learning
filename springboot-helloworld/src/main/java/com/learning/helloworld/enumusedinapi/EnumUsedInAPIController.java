package com.learning.helloworld.enumusedinapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Package: com.learning.helloworld.jsonignoreproperties
 * @Description: EnumUsedInAPIController
 * @Author: Sammy
 * @Date: 2020/12/14 21:00
 */
@RestController
@Slf4j
@RequestMapping("enu")
public class EnumUsedInAPIController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("getOrderStatusClient")
	public void getOrderStatusClient() {
		StatusEnumClient result = restTemplate.getForObject("http://localhost:8990/enu/getOrderStatus", StatusEnumClient.class);
		log.info("result {}", result);
	}

	@GetMapping("getOrderStatus")
	public StatusEnumServer getOrderStatus() {
		return StatusEnumServer.CANCELED;
	}

	@PostMapping("queryOrdersByStatusList")
	public List<StatusEnumServer> queryOrdersByStatusList(@RequestBody List<StatusEnumServer> enumServers) {
		enumServers.add(StatusEnumServer.CANCELED);
		return enumServers;
	}


	@GetMapping("queryOrdersByStatusListClient")
	public void queryOrdersByStatusListClient() {
		List<StatusEnumClient> request = Arrays.asList(StatusEnumClient.CREATED, StatusEnumClient.PAID);
		HttpEntity<List<StatusEnumClient>> entity = new HttpEntity<>(request, new HttpHeaders());
		List<StatusEnumClient> response = restTemplate.exchange("http://localhost:8990/enu/queryOrdersByStatusList",
				HttpMethod.POST, entity, new ParameterizedTypeReference<List<StatusEnumClient>>() {}).getBody();
		log.info("result {}", response);
	}
}
