package com.dwt;

public enum CheckEnums {

	EASY_PASSWD("10000","简单密码aaa"),
	COMPLEY_PASSWD("000000","复杂密码"),
	;

	private String respCode;

	private String respMsg;

	CheckEnums(String respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
}
