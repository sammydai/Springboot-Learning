package com.learning.helloworld.jsonignoreproperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Package: com.learning.helloworld.jsonignoreproperties
 * @Description: APIResultWrong
 * @Author: Sammy
 * @Date: 2020/12/14 18:31
 */
@Data
public class APIResultWrong {
	private boolean success;
	private int code;

	public APIResultWrong() {}

	@JsonCreator
	public APIResultWrong(@JsonProperty(value = "code") int code) {
		this.code = code;
		if (code == 2000) {
			success = true;
		} else {
			success = false;
		}
	}
}
