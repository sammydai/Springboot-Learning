package com.learning.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Package: com.learning.aop.config
 * @Description: WebMvcConfg
 * @Author: Sammy
 * @Date: 2020/12/17 16:53
 */
@Configuration
@Slf4j
public class WebMvcConfg implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		registry.addFormatter(new Formatter<Date>() {
			//print将对象转换为字符串
			@Override
			public String print(Date date, Locale locale) {
				 log.info("==========>print");
				 return Long.valueOf(date.getTime()).toString();
			}

			//parse将字符串转换为对象
			@Override
			public Date parse(String date, Locale locale) throws ParseException {
				log.info("========>parse");
				return new Date(Long.parseLong(date));
			}
		});
	}
}
