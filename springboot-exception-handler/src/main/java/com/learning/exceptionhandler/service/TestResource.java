package com.learning.exceptionhandler.service;

import org.springframework.stereotype.Service;

/**
 * @Package: com.learning.exceptionhandler.templatemethod
 * @Description: AutoCloseable
 * @Author: Sammy
 * @Date: 2020/12/18 12:09
 */
@Service
public class TestResource implements AutoCloseable{
	public void read() throws Exception{
		throw new RuntimeException("read error");
	}

	@Override
	public void close() throws Exception {
		throw new RuntimeException("close error");
	}
}
