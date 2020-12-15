package com.learning.transaction.service;

import com.learning.transaction.domain.UserEntity;
import com.learning.transaction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	@Autowired
	private SubUserService subUserService;

	/**
	 * 通过this调用createUserEntity事务不会生效
	 * @param name
	 * @return
	 */
	public int createUserWrong1(String name) {
		try {
			this.createUserEntity(new UserEntity(name));
		}
		catch (Exception ex) {
			log.error("create user failed because {}", ex.getMessage());
		}
		return userRepository.findByName(name).size();
	}

	/**
	 * controller直接调用此方法正确
	 * @param userEntity
	 */
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

	/**
	 * 异常无法传播出方法，导致事务无法回滚
	 * 异常被捕捉到了，没有传播出方法，异常没有传播出去
	 * @param name
	 */
	@Transactional
	public void createUserCatchExceptionWrong(String name) {
		try {
			userRepository.save(new UserEntity(name));
			throw new RuntimeException("error");
		} catch (Exception ex) {
			log.error("create user failed", ex);
		}
	}

	/**
	 * 方法传播出去的是受检异常（非RuntimeException）
	 * 事务不会回滚
	 * @param name
	 * @throws IOException
	 */
	@Transactional
	public void createUserNonRunExcepitonWrong(String name) throws IOException {
		userRepository.save(new UserEntity(name));
		otherTask();
	}

	//因为文件不存在，一定会抛出一个IOException
	private void otherTask() throws IOException {
		Files.readAllLines(Paths.get("file-that-not-exist"));
	}

	/**
	 * 自己手动出发回滚操作
	 * @param name
	 */
	@Transactional(rollbackFor = Exception.class)
	public void createUserManulRollbackRight(String name) {
		try {
			userRepository.save(new UserEntity(name));
			throw new RuntimeException("error");
		} catch (Exception ex) {
			log.error("create user failed", ex);
			//动设置让当前事务处于回滚状态：
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	/**
	 * 在注解中声明，期望遇到所有的 Exception 都回滚事务（来突破默认不回滚受检异常的限制）
	 * 生命遇到所有异常，都会发生回滚
	 * @param name
	 * @throws IOException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void createUserExcepAnnotationRight(String name) throws IOException {
		userRepository.save(new UserEntity(name));
		otherTask();
	}

	/**
	 * 原因是，主方法注册主用户的逻辑和子方法注册子用户的逻辑是同一个事务，
	 * 子逻辑标记了事务需要回滚，主逻辑自然也不能提交了。
	 * ﻿Participating transaction failed -marking existing transaction as rollback-only
	 * @param entity
	 */
	@Transactional
	public void createUserWrong(UserEntity entity) {
		createMainUser(entity);
		try {
			subUserService.createSubUserWithExceptionWrong(entity);
		} catch (Exception ex) {
			/**
			 * 虽然捕获了异常，但是因为主任务没有开启新事务，
			 * 而当前事务因为子任务的异常已经被标记为rollback了，所以最终还是会回滚。
 			 */
			log.error("create sub user error:{}", ex.getMessage());
		}
	}

	/**
	 * 子方法抛出了异常，主方法也被回滚了
	 * @param entity
	 */
	@Transactional
	public void createUserSubExceptionWrong(UserEntity entity) {
		createMainUser(entity);
		subUserService.createSubUserWithExceptionWrong(entity);
	}

	@Transactional
	public void createUserRight(UserEntity entity) {
		createMainUser(entity);
		try {
			subUserService.createSubUserWithExceptionRight(entity);
		} catch (Exception ex) {
			//捕获异常，防止传播出去，把主方法回滚
			log.error("create sub user error:{}", ex.getMessage());
		}
	}

	private void createMainUser(UserEntity userEntity) {
		userRepository.save(userEntity);
		log.info("createMainUser finish");
	}

}
