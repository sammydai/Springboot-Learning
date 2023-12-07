package com.dwt.redis.service;

import cn.hutool.core.bean.BeanUtil;
import com.dwt.redis.entity.Company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 使用RedisTemplate
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/7 13:57]
 */
@Service
@Slf4j
public class RedisServiceTemplate implements RedisService {

	@Autowired
	private CacheOperationService cacheOperationService;

	private final StringRedisTemplate stringRedisTemplate;

	private final ObjectMapper objectMapper;

	@Autowired
	public RedisServiceTemplate(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
		this.stringRedisTemplate = stringRedisTemplate;
		this.objectMapper = objectMapper;
	}

	public static final String USER_UID_PREFIX = "user:uid:";

	public void setCacheOperationService(CacheOperationService cacheOperationService) {
		this.cacheOperationService = cacheOperationService;
	}

	@Override
	public Company saveUserMap(Company company) {
		String key = USER_UID_PREFIX + company.getLabel();
		log.info("company: {}", company);
		cacheOperationService.hset(key, BeanUtil.beanToMap(company));
		// cacheOperationService.set(key, company);
		log.info("存到redis成功");
		return company;
	}


	@Override
	public Company saveUser(Company company) {
		String key = USER_UID_PREFIX + company.getLabel();
		log.info("company: {}", company);
		cacheOperationService.hset(key, BeanUtil.beanToMap(company));
		// cacheOperationService.set(key, company);
		log.info("存到redis成功");
		return company;
	}

	@Override
	public Company getUser(Integer label) {
		String key = USER_UID_PREFIX + label;
		Company value = (Company) cacheOperationService.get(key);
		return value;
	}

	public void setValue(String key, Company company) {
		String companystr = null;
		try {
			companystr = objectMapper.writeValueAsString(company);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		stringRedisTemplate.opsForValue().set(key, companystr);
	}

	public Company getValue(String key) {
		String value = stringRedisTemplate.opsForValue().get(key);
		try {
			Company company = objectMapper.readValue(value, Company.class);
			return company;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
