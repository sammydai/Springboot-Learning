package com.learning.importl;

import com.learning.importl.config.AppConfig;
import com.learning.importl.domain.NorMal;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Package: com.learning.importl
 * @Description: ImportMethodApplication
 * @Author: Sammy
 * @Date: 2022/7/31 15:14
 */

public class ImportMethodApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		applicationContext.start();
		NorMal norMal = applicationContext.getBean(NorMal.class);
		System.out.println(norMal != null);
	}
}
