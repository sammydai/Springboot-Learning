package com.dwt.springbootmybatis.mapper;

import com.dwt.springbootmybatis.domain.DeptExtObject;
import com.dwt.springbootmybatis.domain.DeptUserObject;
import com.dwt.springbootmybatis.domain.QueryVO;
import com.dwt.springbootmybatis.domain.User;
import com.sun.javafx.tk.TKPulseListener;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
