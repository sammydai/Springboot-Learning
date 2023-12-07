package com.dwt.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Package: com.dwt.redis.config
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 14:44
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setEnableDefaultSerializer(false);
		ObjectMapper om = new ObjectMapper();
		//ALL指定要序列化的域，点进去看源码可以发现有get set等
		// ANY是包括private和public
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		//指定序列化输入的类型，类必须是非final，final修饰的类型会报异常
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		// key序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		// value序列化
		// GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		log.info(">>>>>>>>>>>eee");
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
		CacheProperties.Redis redisProperties = cacheProperties.getRedis();
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		// 工具预览乱码问题
		config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));

		if (redisProperties.getTimeToLive() != null) {
			config = config.entryTtl(redisProperties.getTimeToLive());
		}
		// if (redisProperties.getKeyPrefix() != null) {
		// 	config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
		// }
		if (!redisProperties.isCacheNullValues()) {
			config = config.disableCachingNullValues();
		}
		if (!redisProperties.isUseKeyPrefix()) {
			config = config.disableKeyPrefix();
		}
		return config;
	}

	// @Bean
	// public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
	// 	// 生成一个默认配置，通过config对象即可对缓存进行自定义配置
	// 	// RedisSerializer<String> redisSerializer = new StringRedisSerializer();
	// 	// 使用Jackson2JsnRedisSerializer来序列化和反序列化redis的value值
	// 	Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	// 	// 配置序列化
	// 	RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
	// 	config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
	// 	config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
	// 	// 设置缓存的默认过期时间
	// 	config.entryTtl(Duration.ofSeconds(180));
	// 	// 不缓存空值
	// 	config.disableCachingNullValues();
	// 	return  RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
	// }

}
