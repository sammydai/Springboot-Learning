package com.learning.transaction.controller;

import com.learning.transaction.domain.UserEntity;
import com.learning.transaction.service.SubUserService;
import com.learning.transaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

	@Autowired
	private SubUserService subUserService;

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

	@GetMapping("wrong2")
	public int wrong2(@RequestParam("name") String name) {
		log.info("====>invoke wrong3");
		userService.createUserWrong3(name);
		return userService.getUserCount(name);
	}

	@GetMapping("wrong3")
	public int wrong3(@RequestParam("name") String name) throws IOException {
		log.info("====>invoke wrong4");
		userService.createUserWrong4(name);
		return userService.getUserCount(name);
	}


	@GetMapping("wrong4")
	public int wrong(@RequestParam("name") String name) {
		try {
			userService.createUserWrong(new UserEntity(name));
		} catch (Exception ex) {
			log.error("createUserWrong failed, reason:{}", ex.getMessage());
		}
		return userService.getUserCount(name);
	}

	@GetMapping("right4")
	public int right4(@RequestParam("name") String name) {
		try {
			userService.createUserRight(new UserEntity(name));
		} catch (Exception ex) {
			log.error("createUserWrong failed, reason:{}", ex.getMessage());
		}
		return userService.getUserCount(name);
	}
}
