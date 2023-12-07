package com.dwt.redis.controller;

import com.dwt.redis.entity.Company;
import com.dwt.redis.service.RedisServiceAnno;
import com.dwt.redis.service.RedisServiceTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 缓存验证类
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/6 14:44]
 */
@RestController
@Slf4j
@RequestMapping("/api/redis")
public class CompanyController {

	final RedisServiceAnno redisServiceAnno;

	final RedisServiceTemplate redisServiceTemplate;

	@Autowired
	public CompanyController(RedisServiceAnno redisService, RedisServiceTemplate redisServiceTemplate) {
		this.redisServiceAnno = redisService;
		this.redisServiceTemplate = redisServiceTemplate;
	}

	@PostMapping("/query")
	public String queryCompany(@RequestParam String id) {
		return redisServiceAnno.addUser(id);
	}

	@PostMapping("/add")
	public Company addCompany(@RequestBody Company company) {
		return redisServiceAnno.saveUser(company);
	}

	@PostMapping("/get")
	public Company getValue(String key) {
		return redisServiceTemplate.getValue(key);
	}

	@PostMapping("/set")
	public void setValue(@RequestParam String key, @RequestBody Company company) {
		redisServiceTemplate.setValue(key, company);
	}

	@PostMapping("/save")
	public Company saveCompany(@RequestBody Company company) {
		return redisServiceTemplate.saveUser(company);
	}

	@PostMapping("/getuser")
	public Company getValue(@RequestParam Integer label) {
		return redisServiceTemplate.getUser(label);
	}
}
