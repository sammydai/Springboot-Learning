package com.dwt.springboottaskquartz.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @Package: com.dwt.springboottaskquartz.common
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/9 00:31
 */
@Data
public class ApiResponse implements Serializable{

	private String message;

	private Object data;

	public ApiResponse() {
	}

	public ApiResponse(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public static ApiResponse of(String message,Object data){
		return new ApiResponse(message, data);
	}

	public static ApiResponse ok(Object data){
		return new ApiResponse(HttpStatus.OK.getReasonPhrase(), data);
	}

	public static  ApiResponse msg(String message){
		return of(message, null);
	}
}
