package com.dwt.springboottaskquartz.service.impl;

import com.dwt.springboottaskquartz.entity.domain.JobAndTrigger;
import com.dwt.springboottaskquartz.entity.form.JobForm;
import com.dwt.springboottaskquartz.mapper.JobMapper;
import com.dwt.springboottaskquartz.service.JobService;
import com.dwt.springboottaskquartz.util.JobUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.dwt.springboottaskquartz.service.impl
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/9 01:04
 */

@Slf4j
@Service
public class JobServiceImpl implements JobService {

	private final Scheduler scheduler;

	private final JobMapper jobMapper;

	@Autowired
	public JobServiceImpl(Scheduler scheduler, JobMapper jobMapper) {
		this.scheduler = scheduler;
		this.jobMapper = jobMapper;
	}

	@Override
	public void addJob(JobForm form) throws Exception {
		scheduler.start();

		// 构建Job信息
		JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(form.getJobClassName()).getClass()).withIdentity(form.getJobClassName(), form.getJobGroupName()).build();

		// Cron表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());

		//根据Cron表达式构建一个Trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobClassName(), form.getJobGroupName()).withSchedule(cron).build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			log.error("【定时任务】创建失败！", e);
			throw new Exception("【定时任务】创建失败！");
		}
	}

	@Override
	public void deleteJob(JobForm form) throws SchedulerException {
		scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
		scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
		scheduler.deleteJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
	}

	@Override
	public void pauseJob(JobForm form) throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey(form.getJobClassName(),form.getJobGroupName()));
	}

	@Override
	public void resumeJob(JobForm form) throws SchedulerException {
		scheduler.resumeJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
	}

	@Override
	public void cronJob(JobForm form) throws Exception {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName());

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			scheduler.rescheduleJob(triggerKey, trigger);

		} catch (SchedulerException e) {
			log.error("【定时任务】更新失败！", e);
			throw new Exception("【定时任务】创建失败！");
		}


	}

/**
 * @description 
 * @param currentPage:
 * @param pageSize:  
 * @return com.github.pagehelper.PageInfo<com.dwt.springboottaskquartz.entity.domain.JobAndTrigger>
 *
 */
	@Override
	public PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<JobAndTrigger> list = jobMapper.list();
		return new PageInfo<>(list);
	}
}
