package com.learning.zkconsumer.config;

import com.learning.zkconsumer.logger.SMFeignLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.learning.zkconsumer.config
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
