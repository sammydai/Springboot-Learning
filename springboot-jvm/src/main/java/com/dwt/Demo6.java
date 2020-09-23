package com.dwt;

/**
 * Hello world!
 */
public class Demo6 {
	public static void main(String[] args) {
		Result result = new Demo6().checkPW();
		System.out.println(result);

	}

	private Result checkPW(){
		return Result.ofSuccess(CheckEnums.COMPLEY_PASSWD.getRespMsg());
	}
}
