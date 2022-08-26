package com.learning.starbucks.demo;

import com.learning.starbucks.demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Package: com.learning.starbucks.demo
 * @Description: SpringBucks Application
 * @Author: Sammy
 * @Date: 2020/12/5 15:29
 */
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
public class SpringBucksApplication implements ApplicationRunner{

	@Autowired
	private CoffeeService coffeeService;

	// @Autowired
	// private JedisPool jedisPool;

	@Autowired
	private JedisPoolConfig jedisPoolConfig;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("redis")
	public JedisPoolConfig jedisPoolConfig() {
		return new JedisPoolConfig();
	}

	// @Bean(destroyMethod = "close")
	// public JedisPool jedisPool(@Value("${redis.host}") String host){
	// 	return new JedisPool(jedisPoolConfig, host);
	// }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info(jedisPoolConfig.toString());
		// try (Jedis jedis = jedisPool.getResource()){
		// 	coffeeService.findAllCoffee().forEach(coffee -> {
		// 		jedis.hset("springbucks-menu",
		// 				coffee.getName(),
		// 				Long.toString(coffee.getPrice().getAmountMinorLong()));
		// 	});
		// 	Map<String, String> menu = jedis.hgetAll("springbucks-menu");
		// 	log.info("Menu: {}",menu);
		//
		// 	String price = jedis.hget("springbucks-menu", "espresso");
		// 	log.info("espresso - {}",
		// 			Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
		// }
	}
}
