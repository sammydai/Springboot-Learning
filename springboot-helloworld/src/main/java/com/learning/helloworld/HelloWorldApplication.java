package com.learning.helloworld;

import cn.hutool.core.util.StrUtil;
import com.learning.helloworld.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HelloWorldApplication {

	@Autowired
	private ServerConfig serverConfig;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HelloWorldApplication.class, args);
		// SpringApplication sa = new SpringApplication(HelloWorldApplication.class);
		// sa.run(args);
		// People bean = ApplicationContextUtils.getBean(People.class);
	}

	@GetMapping(value = "/httpserver/hello")
	public String getHello(@RequestParam(name = "who",required = false) String who) {
		if (StrUtil.isBlank(who)) {
			who = "World First this is localhost !!! "+serverConfig.getUrl();
		}
		return StrUtil.format("Hello, {}!", who);
	}

	/*
	//使用配置文件方式，修改Spring默认的objectmapper功能
	@Bean
   	public ObjectMapper objectMapper() {
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
       objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       return objectMapper;
   }
   */

	// @Bean
	// public Jackson2ObjectMapperBuilderCustomizer customizer() {
	// 	return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
	// }

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter){
		return new RestTemplateBuilder()
				.additionalMessageConverters(mappingJackson2HttpMessageConverter)
				.build();
	}

	// @Bean
	// @LoadBalanced
	// RestTemplate restTemplate(){
	// 	return new RestTemplate();
	// }
}
