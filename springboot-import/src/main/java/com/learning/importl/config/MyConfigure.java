package com.learning.importl.config;

import com.learning.importl.registry.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Package: com.learning.importl.config
 * @Description: MyConfigure
 * @Author: Sammy
 * @Date: 2022/7/31 15:14
 */
@Configuration
// @Import(NorMal.class)
// @Import(MyImportSelector.class)
@Import(MyImportBeanDefinitionRegistrar.class)
public class MyConfigure {
}
