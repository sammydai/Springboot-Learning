package com.learning.ioc.repository;

import com.learning.ioc.domain.User;

import java.util.Collection;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/2/26 11:11]
 */
public class UserRepository {

	private Collection<User> users;

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
