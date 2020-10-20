package com.dwt.springbootmybatis.domain;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @Package: com.dwt.springbootmybatis.domain
 * @Description:
 * @Author: Sammy
 * @Date: 2020/9/23 15:31
 */

public class DeptExtObject extends Dept{

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
