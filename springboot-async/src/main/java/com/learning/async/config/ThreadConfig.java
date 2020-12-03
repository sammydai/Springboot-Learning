package com.learning.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Package: com.learning.async.config
 * @Description: Thread Config
 * @Author: Sammy
 * @Date: 2020/11/30 16:28
 */
@Configuration
public class ThreadConfig {

	@Bean("asyncExecutor1")
	public Executor asyncExecutor1() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(20);
		taskExecutor.setQueueCapacity(200);
		taskExecutor.setKeepAliveSeconds(60);
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.setThreadNamePrefix("asyncExecutor1**-");
		taskExecutor.initialize();
		return taskExecutor;
	}
}
