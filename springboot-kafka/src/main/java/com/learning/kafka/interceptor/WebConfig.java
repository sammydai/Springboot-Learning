package com.learning.kafka.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/10/18 16:20]
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

	@Bean
	ThreadPoolInterceptor threadPoolInterceptor() {
		return new ThreadPoolInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(threadPoolInterceptor());
	}

	/**
	 * 创建线程池
	 */
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 获取可用处理器的Java虚拟机的数量（未必能准确获取到CPU核心数量）
		int core = Runtime.getRuntime().availableProcessors();
		log.info("正在创建线程池ThreadPoolTaskExecutor，线程池核心数量：" + core);
		// 核心线程数
		executor.setCorePoolSize(core);
		// 最大线程数
		executor.setMaxPoolSize(core * 2 + 1);
		// 除核心线程外的线程存活时间
		executor.setKeepAliveSeconds(10);
		// 等待队列数量
		executor.setQueueCapacity(50);
		// 设置线程前缀名称
		executor.setThreadNamePrefix("ThreadExecutor-");
		// 设置拒绝策略（线程不够用时由调用的线程处理该任务）
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 停机策略：该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
		// 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
		executor.setWaitForTasksToCompleteOnShutdown(true);
		// 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
		executor.setAwaitTerminationSeconds(60);
		executor.setThreadFactory(new NameThreadFactory("[abc-factory]"));
		return executor;
	}

	@Bean(name = "statisticsDataExecutor")
	public ThreadPoolTaskExecutor statisticsDataExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 获取可用处理器的Java虚拟机的数量（未必能准确获取到CPU核心数量）
		int core = Runtime.getRuntime().availableProcessors();
		log.info("正在创建线程池statisticsDataExecutor，线程池核心数量：" + core);
		// 核心线程数
		executor.setCorePoolSize(core);
		// 最大线程数
		executor.setMaxPoolSize(core * 2 + 1);
		// 除核心线程外的线程存活时间
		executor.setKeepAliveSeconds(10);
		// 等待队列数量
		executor.setQueueCapacity(50);
		// 设置线程前缀名称
		executor.setThreadNamePrefix("statisticsdata-");
		// 设置拒绝策略（线程不够用时由调用的线程处理该任务）
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 停机策略：该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
		// 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
		executor.setWaitForTasksToCompleteOnShutdown(true);
		// 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
		executor.setAwaitTerminationSeconds(60);
		executor.setThreadFactory(new NameThreadFactory("[def-statisticsdata-factory]"));
		return executor;
	}

	@Bean(name = "commonTaskExecutor")
	public ThreadPoolTaskExecutor commonTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 获取可用处理器的Java虚拟机的数量（未必能准确获取到CPU核心数量）
		int core = Runtime.getRuntime().availableProcessors();
		log.info("正在创建线程池commonTaskExecutor，线程池核心数量：" + core);
		// 核心线程数
		executor.setCorePoolSize(core);
		// 最大线程数
		executor.setMaxPoolSize(core * 2 + 1);
		// 除核心线程外的线程存活时间
		executor.setKeepAliveSeconds(10);
		// 等待队列数量
		executor.setQueueCapacity(50);
		// 设置线程前缀名称
		executor.setThreadNamePrefix("commontask-");
		// 设置拒绝策略（线程不够用时由调用的线程处理该任务）
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 停机策略：该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
		// 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
		executor.setWaitForTasksToCompleteOnShutdown(true);
		// 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
		executor.setAwaitTerminationSeconds(60);
		executor.setThreadFactory(new NameThreadFactory("[xyz-commontask-factory]"));
		return executor;
	}
}
