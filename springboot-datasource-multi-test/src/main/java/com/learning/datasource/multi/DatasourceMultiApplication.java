package com.learning.datasource.multi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @description  知识点
 * 1 @Bean是说我这个方法的返回值当做一个Bean。
 *  而@Resource是说我这个方法的参数要按照名字来注入其他的Bean。
 *  @Resoucrce是指参数按照名字来注入其他的Bean，比如fooTxManager(DataSource fooDataSource)
 *  入参DataSource fooDataSource 是之前用@Bean定义过的fooDataSource
 *  这时可以直接用@Resource注入进来
 *
 * 2 自定义两个Datasource，要把SpringBoot自带的Datasource先排除 exclude
 *
 * 3 生成一个datasource的步骤
 *   1）先定义DataSourceProperties 定义数据写在property中
 *   2）根据先定义DataSourceProperties.initializeDataSourceBuilder().build()创建数据源
 *   3）事务管理器PlatformTransactionManager是接口 DataSourceTransactionManager是其实现类
 *   DataSourceTransactionManager（datasource） 这里的入参可以用第二部生成的bean注入
 *
 */

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
public class DatasourceMultiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasourceMultiApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("foo.datasource")
	public DataSourceProperties fooDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource fooDataSource() {
		DataSourceProperties dataSourceProperties = fooDataSourceProperties();
		log.info("============>foo datasource {}",dataSourceProperties.getUrl());
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
		return new DataSourceTransactionManager(fooDataSource);
	}

	@Bean
	@ConfigurationProperties("bar.datasource")
	public DataSourceProperties barDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource barDataSource() {
		DataSourceProperties dataSourceProperties = barDataSourceProperties();
		log.info("==========>bar datasource {}",dataSourceProperties.getUrl());
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager barTxManager(DataSource barDataSource) {
		return new DataSourceTransactionManager(barDataSource);
	}

}
