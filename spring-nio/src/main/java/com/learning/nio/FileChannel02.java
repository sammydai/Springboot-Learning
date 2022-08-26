package com.learning.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Package: com.learning.ioc.nio
 * @Description: FileChannel02
 * @Author: Sammy
 * @Date: 2022/6/26 15:07
 */

public class FileChannel02 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			 // 创建输入流
			File file = new File("./testnio.txt");
			fis = new FileInputStream(file);
			 // 通过 输入流 获得对应的 FileChannel
			FileChannel fileChannel = fis.getChannel();
			// 创建缓冲区
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
			 // 把数据从 fileChannel 读入到缓冲区
			fileChannel.read(byteBuffer);
			// 3. 把数据从 Buffer 中取出
        // 将缓冲区的字节转换成字符串
			String ssres = new String(byteBuffer.array());
			System.out.println(ssres);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
