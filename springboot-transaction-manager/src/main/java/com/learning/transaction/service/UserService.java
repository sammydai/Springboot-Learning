package com.learning.transaction.service;

import com.learning.transaction.domain.UserEntity;
import com.learning.transaction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.learning.transaction.service
 * @Description: UserService
 * @Author: Sammy
 * @Date: 2020/12/2 09:55
 */
@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public int createUserWrong1(String name) {
		try {
			this.createUserEntity(new UserEntity(name));
		}
		catch (Exception ex) {
			log.error("create user failed because {}", ex.getMessage());
		}
		return userRepository.findByName(name).size();
	}

	@Transactional
	public void createUserPublic(UserEntity userEntity) {
		userRepository.save(userEntity);
		if (userEntity.getName().contains("test")) {
			throw new RuntimeException("invalid username!");
		}
	}

	/**
	 * 在这种情况下，依旧会插入test用户，事务没有生效
	 * this对象调用createUserEntity，是没有经过Spring代理的类，所以不会生效
	 * @param userEntity
	 */
	@Transactional
	public void createUserEntity(UserEntity userEntity) {
		userRepository.save(userEntity);
		if (userEntity.getName().contains("test")) {
			throw new RuntimeException("invalid username!");
		}
	}

	public int getUserCount(String name) {
		return userRepository.findByName(name).size();
	}
}
