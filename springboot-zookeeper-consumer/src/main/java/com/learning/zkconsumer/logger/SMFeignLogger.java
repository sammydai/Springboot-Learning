package com.learning.zkconsumer.logger;

/**
 * @Package: com.learning.zkconsumer.logger
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/31 14:06
 */

import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @description spring cloud netfix组件中，feign相关的日志默认是不会输出的，需要自定义配置才能输出，并且Feign只对Debug基本的日志做出响应， 实际业务需要输出Info级别的日志，所以需要做自定义配置，覆盖相关配置Bean。
 * @Exception
 *
 */
@Slf4j
public class SMFeignLogger extends feign.Logger {

	private Logger logger;

	public SMFeignLogger(String name) {
		this(LoggerFactory.getLogger(name));
	}

	public SMFeignLogger(Class<?> clazz) {
		this(LoggerFactory.getLogger(clazz));
	}

	public SMFeignLogger(Logger logger) {
		this.logger = logger;
	}

	public SMFeignLogger() {
		this(feign.Logger.class);
	}

	@Override
	protected void log(String configKey, String format, Object... args) {
		logger.info(String.format(methodTag(configKey) + format, args));
	}

	@Override
	protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
		log.info("===logAndRebufferResponse==>{}",response.request().url());
		return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
	}

}
