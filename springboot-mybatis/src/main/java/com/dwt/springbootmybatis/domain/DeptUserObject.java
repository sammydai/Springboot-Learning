package com.dwt.springbootmybatis.domain;

/**
 * @Package: com.dwt.springbootmybatis.domain
 * @Description:
 * @Author: Sammy
 * @Date: 2020/9/23 13:51
 */

public class DeptUserObject extends User {
	private Dept department;

	public Dept getDepartment() {
		return department;
	}

	public void setDepartment(Dept department) {
		this.department = department;
	}
}
