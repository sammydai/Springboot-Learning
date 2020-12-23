package com.learning.zkconsumer.ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.learning.zkconsumer.ribbon
 * @Description: MyDefaultRibbonConfig
 * @Author: Sammy
 * @Date: 2020/12/21 14:16
 */
@Configuration
public class MyDefaultRibbonConfig {

	@Bean
	public IRule ribbonRule() {
		return new MyRule();
	}

}
