package com.dwt.redis.entity;

import java.io.IOException;
import java.io.Serializable;

/**
 * @Package: com.dwt.redis.entity
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/18 00:12
 */

public class Company implements Serializable{

	private static final long serialVersionUID = -5183147752462717657L;

	private String companyName;

    private Integer label;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Company{" +
				"companyName='" + companyName + '\'' +
				", label=" + label +
				'}';
	}
}
