package com.dwt.springbootmybatis.service;

import com.dwt.springbootmybatis.domain.DeptExtObject;
import com.dwt.springbootmybatis.domain.DeptUserObject;
import com.dwt.springbootmybatis.domain.User;
import com.dwt.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.dwt.springbootmybatis.service
 * @Description: UserService 服务接口
 * @Author: Sammy
 * @Date: 2020/9/23 10:10
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> getUserList(){
		List<User> users = userMapper.getUserList();
		return users;
	}

	public User getUser(int id) {
		User user = userMapper.getUser(id);
		return user;
	}

	public List<User> queryUserByDept(String deptname) {
		List<User> userByDept = userMapper.getUserByDept(deptname);
		return userByDept;
	}

	public List<DeptUserObject> getUserDept() {
		List<DeptUserObject> userDept = userMapper.getUserDept();
		return userDept;
	}

	public List<DeptExtObject> getDeptFullInfo() {
		List<DeptExtObject> deptFullInfo = userMapper.getDeptFullInfo();
		return deptFullInfo;
	}

}
