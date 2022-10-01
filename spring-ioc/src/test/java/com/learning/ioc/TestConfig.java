package com.learning.ioc;

import com.learning.ioc.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: Sammy
 * @Date: 2022/10/1 13:51
 */
@Configuration
public class TestConfig {

	@Bean
	public User user() {
		return new User();
	}
}
