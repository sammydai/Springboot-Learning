package com.learning.aop.config;

import com.learning.aop.annotation.MyScanner;
import org.springframework.context.annotation.Configuration;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/25 14:42]
 */
@Configuration
@MyScanner("com.learning.aop.service")
public class HelloConfig {
}

