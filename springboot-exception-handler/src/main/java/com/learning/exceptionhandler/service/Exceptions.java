package com.learning.exceptionhandler.service;

import com.learning.exceptionhandler.exception.BusinessException;

/**
 * @Package: com.learning.exceptionhandler.templatemethod
 * @Description: Exceptions
 * @Author: Sammy
 * @Date: 2020/12/18 10:49
 */

public class Exceptions {
	//错误方法
	// public static BusinessException ORDEREXISTS = new BusinessException("订单已经存在", 3001);

	//正确方法
	public static BusinessException orderExists() {
		return new BusinessException("订单已经存在", 3001);
	}
}
