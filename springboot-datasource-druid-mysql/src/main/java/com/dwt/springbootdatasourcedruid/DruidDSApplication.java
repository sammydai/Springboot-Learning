package com.dwt.springbootdatasourcedruid;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class DruidDSApplication implements CommandLineRunner {

	@Resource
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DruidDSApplication.class, args);
	}

	private void showConnection() throws SQLException {
		log.info("==========>dataSource:{}",dataSource.getClass());
		Connection connection = dataSource.getConnection();
		log.info("==========>connection:{}",connection.toString());
		log.info("==========>connection url:{}",connection.getMetaData().getURL());
	}

	private void showData() {
		DruidDataSource druidDataSource = (DruidDataSource) dataSource;
		log.info("=========>maxActive{}",druidDataSource.getMaxActive());
		log.info("=========>initialSize{}",druidDataSource.getInitialSize());
	}

	@Override
	public void run(String... args) throws Exception {
		showConnection();
		showData();
	}


}
