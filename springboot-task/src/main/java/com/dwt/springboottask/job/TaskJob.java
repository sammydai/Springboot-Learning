package com.dwt.springboottask.job;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Package: com.dwt.springboottask.job
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 21:33
 */
@Component
@Slf4j
public class TaskJob {

	@Scheduled(cron = "0/10 * * * * ?")
	public void job1() {
		log.info("【job1】开始执行：{}", DateUtil.formatDateTime(new Date()));
	}

	/**
	 * 从启动时间开始，间隔 2s 执行
	 * 固定间隔时间
	 */
	@Scheduled(fixedRate = 2000)
	public void job2() {
		log.info("【job2】开始执行：{}", DateUtil.formatDateTime(new Date()));
	}

	/**
	 * 从启动时间开始，延迟 5s 后间隔 4s 执行
	 * 固定等待时间
	 */
	@Scheduled(fixedDelay = 4000, initialDelay = 5000)
	public void job3() {
		log.info("【job3】开始执行：{}", DateUtil.formatDateTime(new Date()));
	}
}
