package com.dwt.redission.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁工具类
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/8 14:10]
 */
@Slf4j
@Component
public class DistributedRedisLock {

	@Autowired
	private RedissonClient redissonClient;

	public boolean lock(String lockName) {
		if (redissonClient == null) {
			log.info("DistributedRedisLock redissonClient is null");
			return false;
		}
		try {
			RLock lock = redissonClient.getLock(lockName);
			lock.lock(15, TimeUnit.SECONDS);
			log.info("Thread [{}] DistributedRedisLock lock [{}] success", Thread.currentThread().getName(), lockName);
			return true;
		} catch (Exception e) {
			log.error("DistributedRedisLock lock [{}] Exception:", lockName, e);
			return false;
		}
	}

	public boolean releaselock(String lockName) {
		if (redissonClient == null) {
			log.info("DistributedRedisLock redissonClient is null");
			return false;
		}
		try {
			RLock lock = redissonClient.getLock(lockName);
			lock.unlock();
			log.info("Thread [{}] DistributedRedisLock unlock [{}] success", Thread.currentThread().getName(), lockName);
			return true;
		} catch (Exception e) {
			log.error("DistributedRedisLock unlock [{}] Exception:", lockName, e);
			return false;
		}
	}
}