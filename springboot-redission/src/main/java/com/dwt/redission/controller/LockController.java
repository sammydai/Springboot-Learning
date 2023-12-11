package com.dwt.redission.controller;

import com.dwt.redission.utils.DistributedRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式Redis锁测试controller
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/12/8 14:45]
 */
@Slf4j
@RestController
@RequestMapping("/lock")
public class LockController {

	public static final String LOCK = "LOCK";

	@Autowired
	private DistributedRedisLock distributedRedisLock;

	@GetMapping("/testlock")
	public void testlock() {
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				distributedRedisLock.lock(LOCK);
			}).start();
		}
	}

	@PostMapping("/transaction")
	public void post() {
		try {
			if (distributedRedisLock.lock(LOCK)) {
				// 业务逻辑
				log.info("开始业务逻辑");
				Thread.sleep(10000);
				log.info("结束业务逻辑");
			} else {
				// 处理获取锁失败的逻辑
				log.info("获取锁失败");
			}
		} catch (Exception e) {
			log.error("处理异常：", e);
		} finally {
			distributedRedisLock.releaselock(LOCK);
		}
	}

}
