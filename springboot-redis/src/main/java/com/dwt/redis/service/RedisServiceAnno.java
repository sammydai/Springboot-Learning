package com.dwt.redis.service;

import com.dwt.redis.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 使用注解方式
 *
 * @author Sammy
 * @date 2023/12/07
 */
@Service
@Slf4j
public class RedisServiceAnno {

	private final String USER_UID_PREFIX = "'userCache'+";

	/**
	 * 缓存查询
	 * <p>
	 * 使用相同参数的情况下，直接返回缓存中的值，不执行该方法
	 * 只有当key键不存在的时候，才执行方法，将方法的结果缓存起来
	 * <p>
	 * 在Redis中的Key值   aboutuser::addUser_456
	 * 在Redis中的Value值 "result for 456"
	 *
	 * @param id
	 * @return {@link String}
	 */
	@Cacheable(value = "aboutuser", key = "'addUser_'+#id")
	public String addUser(String id) {
		log.info("执行该方法: {}", id);
		return "result for " + id;
	}

	/**
	 * 缓存保存
	 * <p>
	 * 增加缓存，在方法执行前不去检查，不管之前有无缓存，都会更新缓存
	 * <p>
	 * 在Redis中的Key值   aboutuser::userCachexichafactory
	 * 在Redis中的Value值
	 * {
	 * "companyName": "xichafactory",
	 * "label": 17
	 * }
	 *
	 * @param company
	 * @return {@link Company}
	 */
	@CachePut(value = "aboutuser", key = USER_UID_PREFIX + "T(String).valueOf(#company.companyName)")
	public Company saveUser(Company company) {
		log.info("company: {} save to redis", company);
		return company;
	}


}
