package com.learning.ioc;

/**
 * 实现两个整数的除法，输出整数相除的结果，用字符串表示有理数
 * 比如输入11，7，输出1.（571428）
 * 小数循环部分用括号括起来
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/6/27 11:54]
 */
public class Test {

	public String method(Integer a, Integer b) {
		if (b == 0) {
			throw new RuntimeException("除数不能为0");
		}
		while (true) {
			int c1 = a / b;
			int c2 = a % b;

		}

		// Float c2 = Float.valueOf(a%b);
		// if (c2 == 0) {
		// 	return c1.toString();
		// } else {
		// 	return c1.toString() + "." + "("+c2+")";
		// }
	}

	public static void main(String[] args) {
		System.out.println(new Test().method(11, 7));
	}
}
