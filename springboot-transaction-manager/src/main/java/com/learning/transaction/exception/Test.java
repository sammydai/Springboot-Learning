// package com.dwt.springboottransactionmanager;
//
// import FooService;
// import RollbackException;
// import com.learning.transaction.TransactionApplication;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.jdbc.core.JdbcTemplate;
//
// @SpringBootApplication
// @Slf4j
// // @EnableTransactionManagement
// public class Test implements CommandLineRunner{
//
// 	@Autowired
// 	private FooService fooService;
//
// 	@Autowired
// 	private JdbcTemplate jdbcTemplate;
//
// 	// @Autowired
// 	// private TransactionTemplate transactionTemplate;
//
// 	public static void main(String[] args) {
// 		SpringApplication.run(TransactionApplication.class, args);
// 	}
//
// 	@Override
// 	public void run(String... args) {
// 		fooService.insertRecord();
// 		log.info("AAA {}",
// 				jdbcTemplate
// 						.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class));
// 		try {
// 			fooService.insertThenRollback();
// 		} catch (RollbackException e) {
// 			log.info("BBB {}",
// 					jdbcTemplate
// 							.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
// 		}
// 		try {
// 			fooService.invokeInsertThenRollback();
// 		} catch (RollbackException e) {
// 			log.info("BBB {}",
// 					jdbcTemplate
// 							.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
// 		}
//
// 	}
//
// 	// private void test1() {
// 	// 	log.info("COUNT BEFORE TRANSACTION: {}",getCount());
// 	// 	transactionTemplate.execute(new TransactionCallbackWithoutResult() {
// 	// 		@Override
// 	// 		protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
// 	// 			jdbcTemplate.execute("INSERT INTO FOO(ID,BAR) VALUES (1,'hahaha')");
// 	// 			log.info("COUNT IN TRANSACTION: {}", getCount());
// 	// 			transactionStatus.setRollbackOnly();
// 	// 		}
// 	// 	});
// 	// 	log.info("COUNT AFTER TRANSACTION: {}",getCount());
// 	// }
//
// 	private long getCount() {
// 		return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO")
// 				.get(0).get("CNT");
// 	}
// }
