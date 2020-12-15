package com.learning.mybatis.controller;

import com.learning.mybatis.domain.DeptExtObject;
import com.learning.mybatis.domain.DeptUserObject;
import com.learning.mybatis.domain.QueryVO;
import com.learning.mybatis.domain.User;
import com.learning.mybatis.service.UserService;
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
}
