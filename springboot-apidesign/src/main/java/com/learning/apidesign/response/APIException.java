package com.learning.apidesign.response;

import lombok.Getter;

/**
 * @Package: com.learning.apidesign.response
 * @Description: APIException
 * @Author: Sammy
 * @Date: 2020/12/21 21:43
 */

public class APIException extends RuntimeException {
	@Getter
 	private int errorCode;

	@Getter
	private String errorMessage;

	 public APIException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public APIException(Throwable cause, int errorCode, String errorMessage) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
