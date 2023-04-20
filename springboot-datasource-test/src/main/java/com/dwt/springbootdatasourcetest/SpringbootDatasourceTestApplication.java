package com.dwt.springbootdatasourcetest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class SpringbootDatasourceTestApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDatasourceTestApplication.class, args);
	}

	private void showConnection() throws SQLException {
		log.info(dataSource.toString());
		Connection connection = dataSource.getConnection();
		log.info(connection.toString());
		connection.close();
	}

	private void showData() {
		jdbcTemplate.queryForList("SELECT * FROM FOO")
				.forEach(row -> log.info(row.toString()));
	}

	@Override
	public void run(String... args) throws Exception {
		showConnection();
		showData();
	}


}
