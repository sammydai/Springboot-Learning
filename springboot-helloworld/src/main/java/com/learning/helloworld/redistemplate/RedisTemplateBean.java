// package com.learning.helloworld.redistemplate;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.RedisSerializer;
//
// /**
//  * @Package: com.learning.helloworld.redistemplate
//  * @Description: RedisTemplate Bean
//  * @Author: Sammy
//  * @Date: 2020/12/13 22:24
//  */
// @Configuration
// public class RedisTemplateBean {
//
// 	@Bean
// 	public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
// 		RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
// 		redisTemplate.setConnectionFactory(redisConnectionFactory);
// 		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
// 		ObjectMapper objectMapper = new ObjectMapper();
// 		// objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
// 		//把类信息作为属性写入value中
// 		objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
// 		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
// 		redisTemplate.setKeySerializer(RedisSerializer.string());
// 		// redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
// 		redisTemplate.setValueSerializer(RedisSerializer.json());
// 		redisTemplate.setHashKeySerializer(RedisSerializer.string());
// 		// redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
// 		redisTemplate.setHashValueSerializer(RedisSerializer.json());
// 		redisTemplate.afterPropertiesSet();
// 		return redisTemplate;
// 	}
// }
