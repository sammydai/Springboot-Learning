package com.learning.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Package: com.learning.ioc.nio
 * @Description: FileChannel03
 * @Author: Sammy
 * @Date: 2022/6/26 15:16
 */

public class FileChannel03 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			File file = new File("./testnio.txt");
			fis = new FileInputStream(file);
			fos = new FileOutputStream("./testnio2.txt");
			FileChannel inputStreamChannel = fis.getChannel();
			FileChannel outputStreamChannel = fos.getChannel();

			ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 10);
			int read = 0;
			while (read != -1) {
				byteBuffer.clear();
				read = inputStreamChannel.read(byteBuffer);
				byteBuffer.flip();
				outputStreamChannel.write(byteBuffer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
