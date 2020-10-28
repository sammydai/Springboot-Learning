package com.dwt.jvm8.time;

import com.sun.deploy.security.MozillaJSSDSASignature;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 * @Package: com.dwt.jvm8.time
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/28 13:06
 */

public class DateAPITest {
	public static void main(String[] args) {

		LocalDate date = LocalDate.now();
		int year = date.get(ChronoField.YEAR);
		int month = date.get(ChronoField.MONTH_OF_YEAR);
		int day = date.get(ChronoField.DAY_OF_MONTH);

		System.out.println(year);
		System.out.println(month);
		System.out.println(day);

		LocalDate localParser = LocalDate.parse("2014-03-28");


	}
}
