package com.dwt.springboothelloworld;

import cn.hutool.core.util.StrUtil;
import com.dwt.springboothelloworld.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@RestController
@SpringBootApplication
public class SpringbootIOApplication {

	@Autowired
	private ServerConfig serverConfig;

	public static void main(String[] args) {

		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {


			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/daiwenting/Documents/lua/test.txt")));
			String buffstr = null;
			while ((buffstr = bufferedReader.readLine()) != null) {
				if ("exit".equals(buffstr)) {
					System.exit(-1);
				}
				bufferedWriter.write(buffstr);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

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

	private static void readFileBuffer() throws Exception {
		InputStreamReader isr = new InputStreamReader(new FileInputStream("/Users/daiwenting/Documents/lua/test.txt"), "utf-8");
		BufferedReader bufferedReader = new BufferedReader(isr);
		String bstr = null;
		while ((bstr = bufferedReader.readLine()) != null) {
			System.out.println(bstr);
		}
	}

	private static void readFileStreamReader() throws Exception {
		InputStreamReader isr = new InputStreamReader(new FileInputStream("/Users/daiwenting/Documents/lua/test.txt"), "utf-8");
		int len;
		char[] chars = new char[1024];
		while ((len = isr.read(chars)) != -1) {
			System.out.println(new String(chars, 0, len));
		}
	}

	private static void readFileReader() throws Exception {
		FileReader fr = new FileReader("/Users/daiwenting/Documents/lua/test.txt");
		char[] chars = new char[1024];
		int len;
		while ((len = fr.read(chars)) != -1) {
			System.out.println(new String(chars, 0, len));
		}
		fr.close();
	}

	private static void readFileStream() throws Exception {
		InputStream fis = new FileInputStream("/Users/daiwenting/Documents/lua/test.txt");
		byte[] bytes = new byte[1024];
		int len;
		StringBuffer sb = new StringBuffer();
		while ((len = fis.read(bytes)) != -1) {
			sb.append(new String(bytes, 0, len));
		}
		System.out.println(sb.toString());
	}

	@GetMapping(value = "/httpserver/hello")
	public String getHello(@RequestParam(name = "who", required = false) String who) {
		if (StrUtil.isBlank(who)) {
			who = "World First this is localhost !!! " + serverConfig.getUrl();
		}
		return StrUtil.format("Hello, {}!", who);
	}

}
