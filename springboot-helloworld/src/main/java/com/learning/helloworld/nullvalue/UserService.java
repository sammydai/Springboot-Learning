package com.learning.helloworld.nullvalue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @Package: com.learning.helloworld.templatemethod
 * @Description: User Service
 * @Author: Sammy
 * @Date: 2020/12/8 16:42
 */
@RestController
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("wrong")
	public User wrong(@RequestBody User user) {
		user.setNickname(String.format("guest%s", user.getName()));
		return userRepository.save(user);
	}
}
