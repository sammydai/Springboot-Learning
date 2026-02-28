package com.dwt.redis.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2026/2/2 09:59]
 */
@Data
public class Result<T> implements Serializable {
	private boolean success;
	private String message;
	private T data;
	private String code;  // 状态码

	// 成功静态方法
	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(T data) {
		Result<T> result = new Result<>();
		result.setSuccess(true);
		result.setCode("200");
		result.setMessage("操作成功");
		result.setData(data);
		return result;
	}

	public static <T> Result<T> success(String message, T data) {
		Result<T> result = new Result<>();
		result.setSuccess(true);
		result.setCode("200");
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	// 失败静态方法
	public static <T> Result<T> error() {
		return error("操作失败");
	}

	public static <T> Result<T> error(String message) {
		return error("500", message);
	}

	public static <T> Result<T> error(String code, String message) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

	public static <T> Result<T> error(String code, String message, T data) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(code);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
}
