package com.learning.door.config;

import com.learning.door.aspect.DoJoinPoint;
import com.learning.door.service.StarterService;
import com.learning.door.service.StarterServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(StarterService.class)
@EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfigure {

    @Autowired
    private StarterServiceProperties properties;

    @Bean
        // @ConditionalOnMissingBean
        // @ConditionalOnProperty(prefix = "itstack.door", value = "enabled", havingValue = "true")
    StarterService starterService() {
        return new StarterService(properties.getUserStr());
    }

    @Bean
        // @ConditionalOnMissingBean
        // @ConditionalOnProperty(prefix = "itstack.door", value = "enabled", havingValue = "true")
    DoJoinPoint doJoinPoint() {
        return new DoJoinPoint();
    }

}
