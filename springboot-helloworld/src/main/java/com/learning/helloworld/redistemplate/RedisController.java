package com.learning.helloworld.redistemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @Package: com.learning.helloworld.controller
 * @Description: Redis Controller
 * @Author: Sammy
 * @Date: 2020/12/13 18:32
 */

@RestController
@RequestMapping("redistemplate")
@Slf4j
public class RedisController {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisTemplate<String, User> userRedisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void init() throws JsonProcessingException {
		redisTemplate.opsForValue().set("redisTemplate", new User("zhuye", 36));
		stringRedisTemplate.opsForValue().set("stringRedisTemplate", objectMapper.writeValueAsString(new User("zhuye", 36)));
	}

	@GetMapping("wrong")
	public void wrong() {
		log.info("redisTemplate get {}", redisTemplate.opsForValue().get("stringRedisTemplate"));
		log.info("stringRedisTemplate get {}", stringRedisTemplate.opsForValue().get("redisTemplate"));
	}

	@GetMapping("right")
	public void right() throws IOException {
		//redisTemplate读取的数据是Object类型，无需反序列化就可以拿到实际对象
		User userTemplate = (User) this.redisTemplate.opsForValue().get("redisTemplate");
		log.info("redisTemplate get {}", (User) userTemplate);

		//使用StringRedisTemplate，虽然Key正常，但是Value存取需要手动序列化成字符串
		User userStringRedisTemplate = objectMapper.readValue(this.stringRedisTemplate.opsForValue().get("stringRedisTemplate"), User.class);
		log.info("stringRedisTemplate get {}", userStringRedisTemplate);
	}

	/**
	 * userRedisTemplate 获取到的 Value，是 LinkedHashMap 类型的
	 * get{name=zhuye, age=36} class java.util.LinkedHashMap
	 */
	@GetMapping("right2")
	public void right2() {
		User user = new User("zhuye", 36);
		userRedisTemplate.opsForValue().set(user.getName(),user);
		User userFromRedis = this.userRedisTemplate.opsForValue().get(user.getName());
		log.info("userRedisTemplate get{} {}", userFromRedis, userFromRedis.getClass());
		log.info("stringRedisTemplate get {}",stringRedisTemplate.opsForValue().get(user.getName()));
	}
}
