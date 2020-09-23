package com.dwt.springbootmybatis.controller;

import com.dwt.springbootmybatis.domain.DeptExtObject;
import com.dwt.springbootmybatis.domain.DeptUserObject;
import com.dwt.springbootmybatis.domain.User;
import com.dwt.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.dwt.springbootmybatis.controller
 * @Description:
 * @Author: Sammy
 * @Date: 2020/9/23 09:25
 */

@RestController
public class UserController {

	@Autowired
	private UserService userService;

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
}
