package com.dwt.springbootexceptionhandler.exception;

import com.dwt.springbootexceptionhandler.constant.Status;
import lombok.Getter;

/**
 * @Package: com.dwt.springbootexceptionhandler.exception
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 02:12
 */
@Getter
public class PageException extends BaseException {
	public PageException(Status status) {
		super(status);
	}

	public PageException(Integer code, String message) {
		super(code, message);
	}
}
