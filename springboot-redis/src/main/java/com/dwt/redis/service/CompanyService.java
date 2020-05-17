package com.dwt.redis.service;

/**
 * @Package: com.dwt.redis.service
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/18 00:13
 */

public interface CompanyService {
	Integer isCompany(String companyName);

    Integer add();

    Integer del();

    void set(String key, String value);

    String get(String key);
}
