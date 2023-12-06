package com.dwt.redis.controller;

import com.dwt.redis.entity.Company;
import com.dwt.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/6 14:44]
 */
@RestController
@Slf4j
@RequestMapping("/api/redis")
public class CompanyController {

	final RedisService redisService;

	@Autowired
	public CompanyController(RedisService redisService) {
		this.redisService = redisService;
	}

	@PostMapping("/add")
	public String addCompany(String id) {
		return redisService.addUser(id);
	}

	@PostMapping("/get")
	public Company getValue(String key) {
		return redisService.getValue(key);
	}

	@PostMapping("/set")
	public void setValue(@RequestParam String key, @RequestBody Company company) {
		redisService.setValue(key, company);
	}
}
