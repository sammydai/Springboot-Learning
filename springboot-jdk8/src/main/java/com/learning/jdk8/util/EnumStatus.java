package com.learning.jdk8.util;

public enum EnumStatus {
	PAID(1001,"已支付"),
	FINISHED(1003,"已完成");

	public final Integer statusmsg;

	public final String desc;

	EnumStatus(Integer statusmsg, String desc) {
		this.statusmsg = statusmsg;
		this.desc = desc;
	}

	public Integer getStatusmsg() {
		return statusmsg;
	}

	public String getDesc() {
		return desc;
	}
}
