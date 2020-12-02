package com.learning.transaction.controller;

import com.learning.transaction.domain.UserEntity;
import com.learning.transaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.learning.transaction.controller
 * @Description: User Controller
 * @Author: Sammy
 * @Date: 2020/12/2 10:10
 */
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("wrong1")
	public int wrong1(@RequestParam("name") String name) {
		return userService.createUserWrong1(name);
	}

	@GetMapping("right1")
	public int right1(@RequestParam("name") String name) {
		try {
			userService.createUserPublic(new UserEntity(name));
		} catch (Exception ex) {
			log.error("==>create user failed because {}", ex.getMessage());
		}
		return userService.getUserCount(name);
	}
}
