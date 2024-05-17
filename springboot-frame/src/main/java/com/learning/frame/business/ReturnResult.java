package com.learning.frame.business;

import net.sf.json.JSONObject;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2024/5/16 11:36]
 */
public class ReturnResult {

	String datas = "";
	String msg = "";
	String code = "fail";
	boolean useGzip = false;

	public static ReturnResult getInstance4Fail() {
		ReturnResult rr = new ReturnResult();
		rr.code = "fail";
		return rr;
	}

	public static ReturnResult getInstance4Success() {
		ReturnResult rr = new ReturnResult();
		rr.code = "success";
		return rr;
	}

	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		result.put("datas", datas);
		result.put("msg", msg);
		result.put("code", code);
		result.put("useGzip", useGzip);
		return result;
	}
}
