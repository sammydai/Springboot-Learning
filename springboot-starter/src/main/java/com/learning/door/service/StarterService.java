package com.learning.door.service;

import org.springframework.util.StringUtils;

public class StarterService {

	private final String userStr;

	public StarterService(String userStr) {
		this.userStr = userStr;
	}

	public String[] split(String separatorChar) {
		return StringUtils.split(this.userStr, separatorChar);
	}

}
