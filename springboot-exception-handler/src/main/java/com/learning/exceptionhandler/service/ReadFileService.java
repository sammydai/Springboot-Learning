package com.learning.exceptionhandler.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Package: com.learning.exceptionhandler.templatemethod
 * @Description: Read File Service
 * @Author: Sammy
 * @Date: 2020/12/18 10:27
 */
@Service
public class ReadFileService {

	public void readFile() throws IOException {
		Files.readAllLines(Paths.get("a_file"));
	}

	public void createOrderWrong() {
		//这里有问题
		throw Exceptions.orderExists();
	}

	public void cancelOrderWrong() {
		//这里有问题
		throw Exceptions.orderExists();
	}
}
