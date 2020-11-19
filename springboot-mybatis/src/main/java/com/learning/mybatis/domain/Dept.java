package com.learning.mybatis.domain;

/**
 * @Package: com.dwt.springbootmybatis.domain
 * @Description: Department
 * @Author: Sammy
 * @Date: 2020/9/23 13:33
 */

public class Dept {
	private Integer deptId;
	private String deptName;
	private String deptLocation;
	private String deptDescription;
	private String deptManager;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLocation() {
		return deptLocation;
	}

	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}

	public String getDeptDescription() {
		return deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

	public String getDeptManager() {
		return deptManager;
	}

	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "Dept{" +
				"deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				", deptLocation='" + deptLocation + '\'' +
				", deptDescription='" + deptDescription + '\'' +
				", deptManager='" + deptManager + '\'' +
				'}';
	}
}
