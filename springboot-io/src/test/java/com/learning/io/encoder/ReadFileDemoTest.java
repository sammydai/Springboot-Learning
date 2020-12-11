package com.learning.io.encoder;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.Charsets;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class ReadFileDemoTest {

	@Test
	public void readFileEncode() {
		ReadFileDemo readFileDemo = new ReadFileDemo();
		try {
			readFileDemo.readFileEncode();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readFileEncodeError() throws IOException {
		char[] chars = new char[10];
		String content = "";
		try (FileReader fileReader = new FileReader("hello.txt")) {
			int count;
			while ((count = fileReader.read(chars)) != -1) {
				content += new String(chars, 0, count);
			}
		}
		log.info("result:{}", content);
	}

	@Test
	public void readFileEncodeDefault() throws IOException {
		log.info("charset: {}",Charset.defaultCharset());
		Files.write(Paths.get("hello2.txt"), "你好Hi".getBytes(Charsets.UTF_8));
		log.info("bytes: {}", Hex.encodeHexString(Files.readAllBytes(Paths.get("hello2.txt"))).toUpperCase());
	}

	@Test
	public void readFileEncodeRight() throws IOException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream("hello.txt"), Charset.forName("GBK"));
		char[] chars = new char[1024];
		int len;
		String content="";
		while ((len = isr.read(chars)) != -1) {
			content += new String(chars, 0, len);
		}
		log.info("result1: {}", content);
		log.info("result2: {}",Files.readAllLines(Paths.get("hello.txt"), Charset.forName("GBK")).stream().findFirst().orElse(""));
	}

}