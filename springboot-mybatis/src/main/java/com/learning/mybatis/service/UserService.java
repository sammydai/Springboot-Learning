package com.learning.mybatis.service;

import com.learning.mybatis.domain.DeptExtObject;
import com.learning.mybatis.domain.DeptUserObject;
import com.learning.mybatis.domain.QueryVO;
import com.learning.mybatis.domain.User;
import com.learning.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Package: com.learning.mybatis.service
 * @Description: UserService 服务接口
 * @Author: Sammy
 * @Date: 2020/9/23 10:10
 */
@Service
@Slf4j
public class UserService{

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

	public int insertUser() {
		int i = 0;
		User user = new User("tomcat", 22, "usa", "it", new Date());
		try {
			i = userMapper.insertUser(user);
		} catch (Exception e) {
			log.error("insert user错误,{}", e.getMessage());
		}
		return i;
	}

	public int insertUser(User user) {
		int i = userMapper.insertUser(user);
		return i;
	}

	public int insertUserList(List<User> list) {
		int num = 0;
		for (User user : list) {
			num++;
			try {
				if (num == 3) {
					int i = 1 / 0;
				}
				insertUser(user);
				log.info("开始模拟延迟60s");
				Thread.sleep(60000);
				log.info("延迟模拟60s结束");
			} catch (Exception e) {
				log.error("批量处理第{}条数据出问题,{}", num, e);
			}
		}
		return 1;
	}

	public List<User> getUserByName(QueryVO queryVO) {
		List<User> userByName = userMapper.getUserByName(queryVO);
		return userByName;
	}

	@Transactional
	public void testCache() {
		System.out.println("========>第1次执行");
		User user = userMapper.getUser(2);
		System.out.println(user);
		System.out.println("========>第2次执行");
		User user1 = userMapper.getUser(2);
		System.out.println(user1);
	}

	public List<DeptUserObject> getUserDeptLazy() {
		List<DeptUserObject> userDeptLazy = userMapper.getUserDeptLazy();
		return userDeptLazy;
	}
}
