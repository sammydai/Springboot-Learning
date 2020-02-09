package com.dwt.springboottaskquartz.job;

import cn.hutool.core.date.DateUtil;
import com.dwt.springboottaskquartz.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Package: com.dwt.springboottaskquartz.job
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/9 00:37
 */

@Slf4j
public class HelloJob implements BaseJob {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.error("Hello Job 执行时间: {}", DateUtil.now());
	}
}
