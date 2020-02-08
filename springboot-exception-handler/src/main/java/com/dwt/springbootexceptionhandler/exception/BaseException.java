package com.dwt.springbootexceptionhandler.exception;

import com.dwt.springbootexceptionhandler.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Package: com.dwt.springbootexceptionhandler.exception
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/8 02:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException{
	private Integer code;
	private String message;

	public BaseException(Status status) {
		super(status.getMessage());
		this.code = status.getCode();
		this.message = status.getMessage();
	}


	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
}
