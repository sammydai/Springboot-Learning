package com.learning.mybatis.mapper;

import com.learning.mybatis.domain.DeptExtObject;
import com.learning.mybatis.domain.DeptUserObject;
import com.learning.mybatis.domain.QueryVO;
import com.learning.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	public int insertUser(User user);

	public User getUser(int id);

	public List<User> getUserList();

	public List<User> getUserByDept(String dept);

	public List<User> getUserByName(QueryVO queryVO);

	public List<DeptUserObject> getUserDept();

	public List<DeptExtObject> getDeptFullInfo();

	public List<DeptUserObject> getUserDeptLazy();

}
