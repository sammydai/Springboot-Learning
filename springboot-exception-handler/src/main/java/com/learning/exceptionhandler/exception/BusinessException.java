package com.learning.exceptionhandler.exception;

/**
 * @Package: com.learning.exceptionhandler.exception
 * @Description: BusinessException
 * @Author: Sammy
 * @Date: 2020/12/18 10:05
 */

public class BusinessException extends RuntimeException {

	private int code;

	public BusinessException(String message, int code) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
