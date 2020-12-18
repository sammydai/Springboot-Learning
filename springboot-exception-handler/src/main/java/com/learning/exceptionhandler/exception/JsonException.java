package com.learning.exceptionhandler.exception;

import com.learning.exceptionhandler.constant.Status;
import lombok.Getter;

/**
 * @Package: com.dwt.springbootexceptionhandler.exception
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 02:11
 */
@Getter
public class JsonException extends BaseException {
	public JsonException(Status status) {
		super(status);
	}

	public JsonException(Integer code, String message) {
		super(code, message);
	}
}
