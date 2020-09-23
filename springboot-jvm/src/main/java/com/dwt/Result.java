package com.dwt;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/21 13:16
 */

public class Result {
	private boolean complexEnough;
	private String explain;

	public Result(boolean complexEnough, String explain) {
		this.complexEnough = complexEnough;
		this.explain = explain;
	}

	public static Result ofSuccess(String explain) {
		return new Result(true, explain);
	}

	private static Result ofFail(String explain) {
		return new Result(false, explain);
	}

	public boolean isComplexEnough() {
		return complexEnough;
	}

	public void setComplexEnough(boolean complexEnough) {
		this.complexEnough = complexEnough;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Override
	public String toString() {
		return "Result{" +
				"complexEnough=" + complexEnough +
				", explain='" + explain + '\'' +
				'}';
	}
}
