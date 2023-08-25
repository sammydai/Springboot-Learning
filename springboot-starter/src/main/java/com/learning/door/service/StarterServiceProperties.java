package com.learning.door.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 通过"itstack.door前缀的配置获取userStr信息
 */
@ConfigurationProperties("itstack.door")
public class StarterServiceProperties {

	private String userStr;

	public String getUserStr() {
		return userStr;
	}

	public void setUserStr(String userStr) {
		this.userStr = userStr;
	}

}

