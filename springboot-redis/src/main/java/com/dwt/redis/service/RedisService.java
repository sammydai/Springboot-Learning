package com.dwt.redis.service;

import com.dwt.redis.entity.Company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public RedisService(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    @Cacheable(value = "aboutuser", key = "'addUser_'+#id")
    public String addUser(String id) {
        log.info("执行该方法: {}", id);
        return "result for " + id;
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
