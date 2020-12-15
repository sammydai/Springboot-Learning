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

	/**
	 * 异常无法传播出方法，导致事务无法回滚
	 * @param name
	 * @return
	 */
	@GetMapping("catchexception")
	public int wrong2(@RequestParam("name") String name) {
		log.info("====>invoke Catch Exception Wrong");
		userService.createUserCatchExceptionWrong(name);
		return userService.getUserCount(name);
	}

	/**
	 * 方法传播出去的是受检异常（非RuntimeException）
	 * 不会回滚
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@GetMapping("nonrunexception")
	public int wrong3(@RequestParam("name") String name) throws IOException {
		log.info("====>invoke Non Runtime Exception Wrong");
		userService.createUserNonRunExcepitonWrong(name);
		return userService.getUserCount(name);
	}

	/**
	 * 主方法一起被回滚了
	 * @param name
	 * @return
	 */
	@GetMapping("mainmethodrollback")
	public int wrong(@RequestParam("name") String name) {
		try {
			userService.createUserWrong(new UserEntity(name));
		} catch (Exception ex) {
			log.error("createUserWrong failed, reason:{}", ex.getMessage());
		}
		return userService.getUserCount(name);
	}

	@GetMapping("submethodexception")
	public int wrong5(@RequestParam("name") String name) {
		try {
			userService.createUserSubExceptionWrong(new UserEntity(name));
		} catch (Exception ex) {
			log.error("createUserWrong failed, reason:{}", ex.getMessage());
		}
		return userService.getUserCount(name);
	}

	@GetMapping("mainmethodcommit")
	public int right4(@RequestParam("name") String name) {
		try {
			userService.createUserRight(new UserEntity(name));
		} catch (Exception ex) {
			log.error("createUserWrong failed, reason:{}", ex.getMessage());
		}
		return userService.getUserCount(name);
	}

	/**
	 * 自己手动触发回滚操作
	 * @param name
	 * @return
	 */
	@GetMapping("manulrollback")
	public int right3(@RequestParam("name") String name) {
		userService.createUserManulRollbackRight(name);
		return userService.getUserCount(name);
	}

	/**
	 * 期望遇到所有的 Exception 都回滚事务
	 * 加了rollback注解配置
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@GetMapping("exceptionannotation")
	public int right2(@RequestParam("name") String name) throws IOException {
		userService.createUserExcepAnnotationRight(name);
		return userService.getUserCount(name);
	}
}
