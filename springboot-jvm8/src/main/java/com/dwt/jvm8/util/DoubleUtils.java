package com.dwt.jvm8.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Package: com.dwt.jvm8.util
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/31 17:42
 */
@Slf4j
public class DoubleUtils {
	public static void main(String[] args) {
testScale();
		// double num1 = 3.35;
		// float num2 = 3.35f;
		// System.out.println(String.format("%.1f", num1));//四舍五入
		// System.out.println(String.format("%.1f", num2));
		//
		// BigInteger i = new BigInteger(String.valueOf(Long.MAX_VALUE));
		// System.out.println(i.add(BigInteger.ONE).toString());
		//
		// try {
		// 	long l = i.add(BigInteger.ONE).longValueExact();
		// } catch (Exception ex) {
		// 	ex.printStackTrace();
		// }

	}

	private static void testDoubleCal(){
		System.out.println(0.1+0.2);
		System.out.println(1.0-0.8);
		System.out.println(4.015*100);
		System.out.println(123.3/100);

		double amount1 = 2.15;
		double amount2 = 1.10;
		if (amount1 - amount2 == 1.05)
			System.out.println("OK");
	}

	private static void testDouble(){
		System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
		System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
		System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
		System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));


		System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
		System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
		System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
		System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));
	}

	private static void testScale() {
		BigDecimal bigDecimal1 = new BigDecimal("100");
		BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(100d));
		BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(100));
		BigDecimal bigDecimal4 = BigDecimal.valueOf(100d);
		BigDecimal bigDecimal5 = new BigDecimal(Double.toString(100));

		print(bigDecimal1); //scale 0 precision 3 result 401.500
		print(bigDecimal2); //scale 1 precision 4 result 401.5000
		print(bigDecimal3); //scale 0 precision 3 result 401.500
		print(bigDecimal4); //scale 1 precision 4 result 401.5000
		print(bigDecimal5); //scale 1 precision 4 result 401.5000
	}

	private static void print(BigDecimal bigDecimal) {
		log.info("scale {} precision {} result {}", bigDecimal.scale(), bigDecimal.precision(), bigDecimal.multiply(new BigDecimal("4.015")));
	}

	public static void testOverFlow(){
		long l = Long.MAX_VALUE;
		System.out.println(l + 1);
		System.out.println(l + 1 == Long.MIN_VALUE);


		try {
			long l2 = Long.MAX_VALUE;
			System.out.println(Math.addExact(l2, 1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
