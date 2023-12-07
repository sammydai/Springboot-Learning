package com.dwt.redis.service;

import com.dwt.redis.entity.Company;

public interface RedisService {

	Company saveUserMap(Company company);

	Company saveUser(Company company);

	Company getUser(Integer label);

}
