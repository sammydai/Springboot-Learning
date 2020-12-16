package com.learning.aop.controller;

import com.learning.aop.annotation.Metrics;
import com.learning.aop.service.UserEntity;
import com.learning.aop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.learning.aop.controller
 * @Description: Metrics Controller
 * @Author: Sammy
 * @Date: 2020/12/16 10:36
 */
@Slf4j
@RestController
@RequestMapping("metricstest")
@Metrics(logParameters = false,logReturn = false)
public class MetricsController {

	@Autowired
	private UserService userService;

	@GetMapping("transaction")
	public int transaction(@RequestParam("name") String name) {
		try {
			userService.createUser(new UserEntity(name));
		} catch (Exception e) {
			log.error("create user failed because {}", e.getMessage());
		}
		return userService.getUserCount(name);
	}

}
