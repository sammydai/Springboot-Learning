package com.learning.mybatis.controller;

import com.learning.mybatis.domain.DeptExtObject;
import com.learning.mybatis.domain.DeptUserObject;
import com.learning.mybatis.domain.QueryVO;
import com.learning.mybatis.domain.User;
import com.learning.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.dwt.springbootmybatis.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/9/23 09:25
 */

@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	//TODO:Mybatis翻页功能
	@RequestMapping("/user")
	public List<User> queryUser(){
		List<User> userList = userService.getUserList();
		return userList;
	}

	@RequestMapping("/user/{id}")
	public User queryById(@PathVariable int id) {
		User user = userService.getUser(id);
		return user;
	}

	@RequestMapping("/user/dept/{dept}")
	public List<User> queryByDept(@PathVariable String dept) {
		List<User> users = userService.queryUserByDept(dept);
		return users;
	}

	@RequestMapping("/user/dept")
	public List<DeptUserObject> queryUserDept() {
		List<DeptUserObject> users = userService.getUserDept();
		return users;
	}

	@RequestMapping("/dept")
	public List<DeptExtObject> queryDept() {
		List<DeptExtObject> deptFullInfo = userService.getDeptFullInfo();
		return deptFullInfo;
	}

	@RequestMapping("/insertuser")
	public int insertUser(){
		int i = userService.insertUser();
		return i;
	}

	@RequestMapping("/user/name/{username}")
	public List<User> queryUserByName(@PathVariable String username) {
		System.out.println(">>>>>>>username"+username);
		QueryVO queryVO = new QueryVO();
		User user = new User();
		user.setUsername(username);
		queryVO.setUser(user);
		List<User> userByName = userService.getUserByName(queryVO);
		return userByName;
	}

	@RequestMapping("/test")
	public void testCache() {
		userService.testCache();
	}

	@RequestMapping("/test1")
	public List<DeptUserObject> getUserDeptLazy() {
		List<DeptUserObject> userDeptLazy = userService.getUserDeptLazy();
		return userDeptLazy;
	}

	@RequestMapping("/admin")
	@Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
	public void test() {
		try {
			List<User> userList = new ArrayList<>();
			User user = new User("tom", 13, "sh", "fire", new Date());
			User user2 = new User("kitty", 12, "sh", "bank", new Date());
			User user3 = new User("ben", 25, "bj", "tianya", new Date());
			User user4 = new User("for", 76, "gz", "hainan", new Date());
			User user5 = new User("kind", 52, "sz", "icon", new Date());
			userList.add(user);
			userList.add(user2);
			userList.add(user3);
			userList.add(user4);
			userList.add(user5);
			userService.insertUserList(userList);
			System.out.println("aaaaaaaaa");
			System.out.println("bbbbbbbbbb");
		} catch (Exception e) {
			log.error("error {}", e.getMessage());
		}
	}
}
