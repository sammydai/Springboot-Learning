package com.learning.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @Package: com.learning.io
 * @Description: BeforeClassAndAfterClassAnnotationsUnitTest
 * @Author: Sammy
 * @Date: 2020/12/12 20:35
 */
@RunWith(JUnit4.class)
@Slf4j
public class BeforeClassAndAfterClassAnnotationsUnitTest {

	/**
	 * 整个类方法只执行一次
	 */
	@BeforeClass
	public static void setup() {
		log.info("startup - creating DB connection");
	}

	@AfterClass
	public static void teardown() {
		log.info("closing DB connection");
	}

	@Test
	public void simpleTest() {
		log.info("simple test");
	}

	@Test
	public void anotherSimpleTest() {
		log.info("another simple test");
	}
}
