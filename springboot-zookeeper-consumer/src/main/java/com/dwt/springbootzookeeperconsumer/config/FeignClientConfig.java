package com.dwt.springbootzookeeperconsumer.config;

import com.dwt.springbootzookeeperconsumer.logger.SMFeignLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.dwt.springbootzookeeperconsumer.config
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/31 13:52
 */

@Configuration
public class FeignClientConfig {
	@Bean
    Logger.Level FeignClientLoggerLevel() {
        return Logger.Level.FULL;
    }

	@Bean
	Logger FeignInfoLogger() {
		return new SMFeignLogger();
	}
}
