package com.learning.helloworld.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldServiceTest {

	@Test
	public void sayHello() {
		HelloWorldService hs = new HelloWorldService();
		assertEquals("This is hello test result: ","Hello World",hs.sayHello());
	}
}