package com.learning.io.encoder;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Package: com.learning.io.encoder
 * @Description: Read File Encode
 * @Author: Sammy
 * @Date: 2020/12/11 22:24
 */
@Slf4j
public class ReadFileDemo {

	public void readFileEncode() throws IOException {
		Files.deleteIfExists(Paths.get("hello.txt"));
		Files.write(Paths.get("hello.txt"), "你好Hi".getBytes(Charset.forName("GBK")));
		log.info("bytes:{}", Hex.encodeHexString(Files.readAllBytes(Paths.get("hello.txt"))).toUpperCase());
	}
}
