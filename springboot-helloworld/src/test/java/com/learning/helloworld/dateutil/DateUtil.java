package com.learning.helloworld.dateutil;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;

/**
 * @Package: com.learning.helloworld.dateutil
 * @Description: Date Util
 * @Author: Sammy
 * @Date: 2020/12/8 12:55
 */

public class DateUtil {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static ThreadLocal<SimpleDateFormat> threadSafeSimpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

	private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
			.appendValue(ChronoField.YEAR)
			.appendLiteral("/")
			.appendValue(MONTH_OF_YEAR)
			.appendLiteral("/")
			.appendValue(DAY_OF_MONTH)
			.appendLiteral(" ")
			.appendValue(ChronoField.HOUR_OF_DAY)
			.appendLiteral(":")
			.appendValue(ChronoField.MINUTE_OF_HOUR)
			.appendLiteral(":")
			.appendValue(ChronoField.SECOND_OF_MINUTE)
			.appendLiteral(".")
			.appendValue(ChronoField.MILLI_OF_SECOND)
			.toFormatter();

	@Test
	public void calendarTest() {
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
	public void dateParse() throws ParseException {
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

	/**
	 * 同一个date，在不同时区下格式化
	 * @throws ParseException
	 */
	@Test
	public void dateDisplay() throws ParseException {
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
	public void zoneTest() {
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

	/**
	 * 格式化: 2020-12-29
	 * 设置日期时间为 2019 年 12 月 29 日，使用大写的 YYYY 来初始化 SimpleDateFormat：
	 * 表示年份的format必须使用yyyy，小写y
	 */
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

	/**
	 * int类型发生了溢出
	 * nextMonth输出：Tue Nov 24 00:17:56 CST 2020
	 */
	@Test
	public void nextMonthWrong() {
		Date today = new Date();
		Date nextMonth = new Date(today.getTime() + 30 * 24 * 60 * 60 * 1000);
		System.out.println(today);
		System.out.println(nextMonth);
	}

	/**
	 * 修复方式就是把 30 改为 30L，让其成为一个Long类型
	 */
	@Test
	public void nextMonthRight() {
		Date today = new Date();
		Date nextMonth = new Date(today.getTime() + 30L * 24 * 60 * 60 * 1000);
		System.out.println("today: " + today);
		System.out.println("nextMonth: " + nextMonth);
	}

	/**
	 * 获取下一个月的方法
	 */
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

	@Test
	public void simpleDateFormatWrong() throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 20; i++) {
			threadPool.submit(() -> {
				for (int j = 0; j < 10; j++) {
					try {
						System.out.println(simpleDateFormat.parse("2020-01-01 11:12:13"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			});
		}
		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.HOURS);
	}

	@Test
	public void simpleDateFormatThreadFix() throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 20; i++) {
			threadPool.submit(() -> {
				for (int j = 0; j < 10; j++) {
					try {
						System.out.println(threadSafeSimpleDateFormat.get().parse("2020-01-01 11:12:13"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			});
		}
		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.HOURS);
	}

	@Test
	public void simpleDateFormatTestFormater() throws ParseException {
		String dateString = "20160901";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		System.out.println("result:" + dateFormat.parse(dateString));
	}

	@Test
	public void dateTimeFormatterTest() {
		//使用刚才定义的DateTimeFormatterBuilder构建的DateTimeFormatter来解析这个时间
		LocalDateTime localDateTime = LocalDateTime.parse("2020/1/2 12:34:56.789", dateTimeFormatter);
		System.out.println(localDateTime.format(dateTimeFormatter));
		String dt = "20160901";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
		System.out.println("result:"+dateTimeFormatter.parse(dt));
	}

	@Test
	public void localDateTimeTest() {
		//创建年月
		LocalDate localDate = LocalDate.now();
		System.out.println("当前的年月时间: "+localDate);
		LocalDate dateTime = LocalDate.of(2019, 9, 10);
		System.out.println("指定的年月时间： "+dateTime);

		int year = localDate.getYear();
		int year1 = localDate.get(ChronoField.YEAR);
		System.out.println("当前年:"+year);
		System.out.println("当前年:"+year1);
		Month month = localDate.getMonth();
		int month1 = localDate.get(MONTH_OF_YEAR);
		System.out.println("当前月:"+month);
		System.out.println("当前月:"+month1);
		int day = localDate.getDayOfMonth();
		int day1 = localDate.get(DAY_OF_MONTH);
		System.out.println("当前天:"+day);
		System.out.println("当前天:"+day1);
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
		System.out.println("当前星期:"+dayOfWeek);
		System.out.println("当前星期:"+dayOfWeek1);

		//创建时间
		LocalTime localTime = LocalTime.of(13, 51, 10);
		LocalTime localTime1 = LocalTime.now();
		System.out.println("当前时间："+localTime);
		System.out.println("当前时间："+localTime1);

		//获取时分秒
		//获取小时
		int hour = localTime.getHour();
		int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
		System.out.println("当前小时："+hour);
		System.out.println("当前小时："+hour1);
		//获取分
		int minute = localTime.getMinute();
		int minute1 =             localTime.get(ChronoField.MINUTE_OF_HOUR);
		System.out.println("当前分钟："+minute);
		System.out.println("当前分钟："+minute1);
		//获取秒
		int second = localTime.getMinute();
		int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
		System.out.println("当前秒："+second);
		System.out.println("当前秒："+second1);
	}

	@Test
	public void localDateTime() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.of(13, 51, 10);
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 55);
		LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
		System.out.println("localDateTime: " + localDateTime);
		System.out.println("localDateTime1: " + localDateTime1);
		System.out.println("localDateTime2: " + localDateTime2);
		LocalDateTime localDateTime3 = localDate.atTime(localTime);
		System.out.println("localDateTime3: " + localDateTime3);
		LocalDateTime localDateTime4 = localTime.atDate(localDate);
		System.out.println("localDateTime4: " + localDateTime4);
	}

	@Test
	public void calculateDateTime() {
		System.out.println("//本月的第一天");
		System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));

		System.out.println("//今年的程序员日");
		System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).plusDays(255));

		System.out.println("//今天之前的一个周六");
		System.out.println(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SATURDAY)));

		System.out.println("//本月最后一个工作日");
		System.out.println(LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
	}


	public static Boolean isFamilyBirthday(TemporalAccessor date) {
		int month = date.get(MONTH_OF_YEAR);
		int day = date.get(DAY_OF_MONTH);
		if (month == Month.FEBRUARY.getValue() && day == 17)
			return Boolean.TRUE;
		if (month == Month.SEPTEMBER.getValue() && day == 21)
			return Boolean.TRUE;
		if (month == Month.MAY.getValue() && day == 22)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	@Test
	public void isFamilyDay() {
		System.out.println("//查询是否是今天要举办生日");
		System.out.println(LocalDate.now().query(DateUtil::isFamilyBirthday));
	}

	@Test
	public void calculateBetweenDay() {
		System.out.println("//计算日期差");
		LocalDate today = LocalDate.of(2019, 12, 12);
		LocalDate specifyDate = LocalDate.of(2019, 10, 1);
		System.out.println(Period.between(specifyDate, today).getDays());
		System.out.println(Period.between(specifyDate, today));
		System.out.println(ChronoUnit.DAYS.between(specifyDate, today));
	}

}
