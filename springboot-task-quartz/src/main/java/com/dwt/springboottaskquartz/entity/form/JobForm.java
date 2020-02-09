package com.dwt.springboottaskquartz.entity.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Package: com.dwt.springboottaskquartz.entity.form
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 22:38
 */

@Data
@Accessors(chain = true)
public class JobForm {
	/**
	 * 定时任务全类名
	 */
	@NotBlank(message = "类名不能为空")
	private String jobClassName;

	/**
	 * 任务组名
	 */
	@NotBlank(message = "任务组名不能为空")
	private String jobGroupName;
	/**
	 * 定时任务cron表达式
	 */
	@NotBlank(message = "cron表达式不能为空")
	private String cronExpression;
}
