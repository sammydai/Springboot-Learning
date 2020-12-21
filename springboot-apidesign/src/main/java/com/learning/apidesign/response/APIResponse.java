package com.learning.apidesign.response;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

/**
 * @Package: com.learning.apidesign.response
 * @Description: APIResponse
 * @Author: Sammy
 * @Date: 2020/12/21 20:33
 */
@Data
public class APIResponse<T> {
	private boolean success; //为false 代表下单请求处理失败
	private T data;
	private int code;
	private String message;
}
