package com.learning.frame.common;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 15:14]
 */
public class ExtJobImpl implements Job {
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// jec.getJobDetail();

	}
}
