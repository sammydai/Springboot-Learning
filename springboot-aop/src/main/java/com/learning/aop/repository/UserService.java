package com.learning.aop.repository;

import com.learning.aop.annotation.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.learning.aop.service
 * @Description: UserService
 * @Author: Sammy
 * @Date: 2020/12/16 10:23
 */
@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Metrics(ignoreException = true)
	public void createUser(UserEntity userEntity) {
		userRepository.save(userEntity);
		if (userEntity.getName().contains("test")) {
			throw new RuntimeException("invalid username!");
		}
	}

	public int getUserCount(String name) {
		return userRepository.findByName(name).size();
	}
}
