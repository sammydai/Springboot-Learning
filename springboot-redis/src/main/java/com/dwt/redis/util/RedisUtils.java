// package com.dwt.redis.util;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.HashOperations;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Component;
// import org.springframework.util.CollectionUtils;
// import sun.font.TrueTypeFont;
//
// import java.util.concurrent.TimeUnit;
//
// /**
//  * @Package: com.dwt.redis.util
//  * @Description:
//  * @Author: Sammy
//  * @Date: 2020/5/17 23:33
//  */
// @Component
// public class RedisUtils {
//
// 	@Autowired
// 	private RedisTemplate<String, Object> redisTemplate;
//
// 	// =============================common============================
//
// 	/**
// 	 * 指定缓存失效时间
// 	 *
// 	 * @param key
// 	 * @param time
// 	 * @return
// 	 */
// 	public boolean expire(String key, long time) {
// 		try {
// 			if (time > 0) {
// 				redisTemplate.expire(key, time, TimeUnit.SECONDS);
// 			}
// 			return true;
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 			return false;
// 		}
// 	}
//
// 	/**
// 	 * 指定缓存失效时间
// 	 *
// 	 * @param key not null
// 	 * @return 时间(秒) 返回0代表为永久有效
// 	 */
// 	public long getExpire(String key) {
// 		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
// 	}
//
// 	public boolean hasKey(String key) {
// 		try {
// 			return redisTemplate.hasKey(key);
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 			return false;
// 		}
// 	}
//
// 	public void del(String... key) {
// 		if (key != null && key.length > 0) {
// 			if (key.length == 1) {
// 				redisTemplate.delete(key[0]);
// 			} else {
// 				redisTemplate.delete(CollectionUtils.arrayToList(key));
// 			}
// 		}
// 	}
//
// 	// ============================String=============================
//
// 	/**
// 	 * 普通缓存获取
// 	 *
// 	 * @param key
// 	 * @return
// 	 */
// 	public Object get(String key) {
// 		return key == null ? null : redisTemplate.opsForValue().get(key);
// 	}
//
// 	/**
// 	 * 普通缓存放入
// 	 *
// 	 * @param key
// 	 * @param value
// 	 * @return
// 	 */
// 	public boolean set(String key, Object value) {
// 		try {
// 			redisTemplate.opsForValue().set(key, value);
// 			return true;
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 			return false;
// 		}
// 	}
//
// 	/**
// 	 * 普通缓存放入并设置时间
// 	 *
// 	 * @param key
// 	 * @param value
// 	 * @param time
// 	 * @return
// 	 */
// 	public boolean set(String key, Object value, long time) {
// 		try {
// 			if (time > 0) {
// 				redisTemplate.opsForValue().set(key, value, time);
// 			} else {
// 				set(key, value);
// 			}
// 			return true;
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 			return false;
// 		}
// 	}
//
// 	public long incr(String key, long delta) {
// 		if (delta < 0) {
// 			throw new RuntimeException("递增因子必须大于0");
// 		}
// 		return redisTemplate.opsForValue().increment(key, delta);
// 	}
//
// 	/**
//      * 递减
//      * @param key 键
//      * @return
//      */
//     public long decr(String key, long delta){
//         if(delta<0){
//             throw new RuntimeException("递减因子必须大于0");
//         }
//         return redisTemplate.opsForValue().increment(key, -delta);
//     }
//
// 	// ================================Map=================================
//
// 	public void hmSet(String key, Object hashKey, Object value) {
//
// 		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//
// 		hash.put(key, hashKey, value);
//
// 	}
// }
//
//
