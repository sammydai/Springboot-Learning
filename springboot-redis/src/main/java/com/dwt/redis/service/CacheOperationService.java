package com.dwt.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Redis API
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/7 13:35]
 */
@Service
@Slf4j
public class CacheOperationService {

	@Autowired
	private RedisTemplate redisTemplate;

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 获得指定格式的所有的key键
	 *
	 * @param patens
	 * @return {@link Set}
	 */
	public Set getKeys(Object patens) {
		return redisTemplate.keys(patens);
	}

	// --------------RedisTemplate 操作 String --------------------

	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			log.error("error:{}", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// --------------RedisTemplate 操作 Hash --------------------

	public Object hget(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}

	public boolean hset(String key, Map<String, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
		return true;
	}

	// --------------RedisTemplate 操作 List --------------------

	public Object lget(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	public boolean lset(String key, Object value) {
		redisTemplate.opsForList().rightPush(key, value);
		return true;
	}
}
