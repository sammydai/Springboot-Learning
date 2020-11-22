package com.learning.io;

import cn.hutool.core.util.StrUtil;
import com.learning.io.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@RestController
@SpringBootApplication
public class IOApplication {

	@Autowired
	private ServerConfig serverConfig;


	private static void writerFileStreamWriter() throws Exception {
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/Users/daiwenting/Documents/lua/test.txt"), "utf-8");
		// osw.write();
		osw.close();
	}

	private static void writeFileWriter() throws Exception {
		FileWriter fileWriter = new FileWriter("/Users/daiwenting/Documents/lua/test.txt", true);
		String s = "新建一个对象";
		fileWriter.write(s);
		fileWriter.flush();
	}

	private static void writeFileStream() throws Exception {
		FileOutputStream fos = new FileOutputStream("/Users/daiwenting/Documents/lua/test.txt", true);
		String str = "JVM类加载机制详解（一）JVM类加载过";
		fos.write(str.getBytes());
	}

	@GetMapping(value = "/httpserver/hello")
	public String getHello(@RequestParam(name = "who", required = false) String who) {
		if (StrUtil.isBlank(who)) {
			who = "World First this is localhost !!! " + serverConfig.getUrl();
		}
		return StrUtil.format("Hello, {}!", who);
	}

}
