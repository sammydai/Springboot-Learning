package com.learning.helloworld.dateutil;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Package: com.learning.helloworld.dateutil
 * @Description: Date Util
 * @Author: Sammy
 * @Date: 2020/12/8 12:55
 */

public class DateUtil {
	public void calendartest() {
		//初始化一个 2019 年 12 月 31 日 11 点 12 分 13 秒
		Date date = new Date(2019, 12, 31, 11, 12, 13);
		System.out.println(date);

		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, 11, 31, 11, 12, 13);
		System.out.println(calendar.getTime());
		Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
		calendar2.set(2019, Calendar.DECEMBER, 31, 11, 12, 13);
		System.out.println(calendar2.getTime());

		System.out.println(new Date(0));
		System.out.println(TimeZone.getDefault().getID() + ":" + TimeZone.getDefault().getRawOffset()/3600000);
	}

	/**
	 * 转换成UTC时间是不同的
	 * @throws ParseException
	 */
	@Test
	public void dateparse() throws ParseException {
		String stringDate = "2020-01-02 22:00:00";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//默认时区解析时间表示
		Date date1 = simpleDateFormat.parse(stringDate);
		System.out.println(date1 + " : " + date1.getTime());
		//new york时区表示
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		Date date2 = simpleDateFormat.parse(stringDate);
		System.out.println(date2 + " : " + date2.getTime());
	}

	@Test
	public void datedisplay() throws ParseException {
		String stringDate = "2020-01-02 22:00:00";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//默认时区格式化输出：
		Date date1 = simpleDateFormat.parse(stringDate);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date1));
		//纽约时区格式化输出
		TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date1));
	}

	@Test
	public void zonetest() {
		//一个时间表示String
		String stringDate = "2020-01-02 22:00:00";
		//初始化3个时区
		ZoneId timeZoneSH = ZoneId.of("Asia/Shanghai");
		ZoneId timeZoneNY = ZoneId.of("America/New_York");
		ZoneId timeZoneJST = ZoneOffset.ofHours(9);
		//格式化器
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//ZonedDateTime 第一个参数：the local dateutil-time 第二个参数：时区
		ZonedDateTime date = ZonedDateTime.of(LocalDateTime.parse(stringDate, dateTimeFormatter), timeZoneJST);
		DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
		System.out.println(timeZoneSH.getId() + outputFormat.withZone(timeZoneSH).format(date));
		System.out.println(timeZoneNY.getId() + outputFormat.withZone(timeZoneNY).format(date));
		System.out.println(timeZoneJST.getId() + outputFormat.withZone(timeZoneJST).format(date));
	}

	@Test
	public void testSimpleDateFormat(){
		Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
		System.out.println("defaultLocale:" + Locale.getDefault());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.DECEMBER, 29,0,0,0);
		SimpleDateFormat YYYY = new SimpleDateFormat("YYYY-MM-dd");
		System.out.println("格式化: " + YYYY.format(calendar.getTime()));
		System.out.println("weekYear:" + calendar.getWeekYear());
		System.out.println("firstDayOfWeek:" + calendar.getFirstDayOfWeek());
		System.out.println("minimalDaysInFirstWeek:" + calendar.getMinimalDaysInFirstWeek());
	}

	@Test
	public void nextMonth() {
		//Calender方法
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.DAY_OF_MONTH,30);
		System.out.println(instance.getTime());

		//Java8 API方法
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.plusDays(30));

		System.out.println("//测试操作日期");
		System.out.println(LocalDateTime.now().minus(Period.ofDays(1)));
	}

}
