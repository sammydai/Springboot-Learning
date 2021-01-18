package com.learning.security.storepassword;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.learning.security.storepassword
 * @Description: Store Password Controller
 * @Author: Sammy
 * @Date: 2021/1/18 10:50
 */
@RestController
@Slf4j
@RequestMapping("storepassword")
public class StorePasswordController {

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	@GetMapping("wrong")
	public UserData wrong1(@RequestParam(value = "name", defaultValue = "主页") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
		UserData userData = new UserData();
		userData.setId(1L);
		userData.setName(name);
		userData.setPassword(DigestUtils.md5Hex(password));
		return userRepository.save(userData);
	}

	@GetMapping("wrong2")
	public UserData wrong2(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
		UserData userData = new UserData();
		userData.setId(1L);
		userData.setName(name);
		userData.setPassword(DigestUtils.md5Hex("salt" + password));
		return userRepository.save(userData);
	}

	@GetMapping("better")
	public UserData better(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
		UserData userData = new UserData();
		userData.setId(1L);
		userData.setName(name);
		userData.setPassword(passwordEncoder.encode(password));
		userRepository.save(userData);
		log.info("match ? {}", passwordEncoder.matches(password, userData.getPassword()));
		return userData;
	}

}
