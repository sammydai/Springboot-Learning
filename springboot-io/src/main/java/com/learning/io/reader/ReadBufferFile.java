package com.learning.io.reader;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.util.stream.Collectors.toList;

/**
 * @Package: com.learning.io.reader
 * @Description: Read Buffer File
 * @Author: Sammy
 * @Date: 2020/12/12 18:46
 */

public class ReadBufferFile {

	public void writeFile() throws IOException {
		Files.write(Paths.get("src.txt"),
				IntStream.rangeClosed(1,1000000).mapToObj(i-> UUID.randomUUID().
						toString()).collect(toList()),UTF_8,CREATE, TRUNCATE_EXISTING);
	}

	public void perByteOperation() throws IOException {
		try (
				FileInputStream fis = new FileInputStream("src.txt");
				FileOutputStream fos = new FileOutputStream("des.txt")) {
			int len;
			while ((len = fis.read()) != -1) {
				fos.write(len);
			}
		}
	}

	public void perByteOperationWith100Buffer() throws IOException {
		try (
				FileInputStream fis = new FileInputStream("src.txt");
				FileOutputStream fos = new FileOutputStream("des.txt")) {
			int len;
			byte[] buffer = new byte[100];
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer,0,len);
			}
		}
	}


	private static void fileChannelOperation() throws IOException {
		FileChannel in = FileChannel.open(Paths.get("src.txt"), StandardOpenOption.READ);
		FileChannel out = FileChannel.open(Paths.get("dest.txt"), CREATE, WRITE);
		in.transferTo(0, in.size(), out);
	}
}
