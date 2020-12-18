package com.learning.exceptionhandler.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Package: com.learning.exceptionhandler.constant
 * @Description: APIResponse
 * @Author: Sammy
 * @Date: 2020/12/18 10:04
 */
@Data
@AllArgsConstructor
public class APIResponse<T> {
	private boolean success;
    private T data;
    private int code;
    private String message;
}
