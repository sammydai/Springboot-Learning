package com.learning.ioc.nio;

import com.oracle.tools.packager.IOUtils;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Package: com.learning.ioc.nio
 * @Description: FileChannel01
 * @Author: Sammy
 * @Date: 2022/6/26 14:58
 */

public class FileChannel01 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		try {
			String str = "Hello,NIO!";
			// 2. 把数据写入 Buffer
        // 创建一个输出流 ， channel
			fos = new FileOutputStream("./testnio.txt");
			 // 通过输出流，获取对应的 FileChannel
        // fileChannel 真实类型是 fileChannelImpl
			FileChannel fileChannel = fos.getChannel();
			 // 创建一个缓冲区
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			// 把数据放入到 byteBuffer
			byteBuffer.put(str.getBytes());
			 // 3. 把 Buffer 的数据传入输出流
    		// 4. 通过 输出流 中的 fileChannel 对象把数据写入
        	// 反转 Buffer
			byteBuffer.flip();
			 // 把 Buffer 的数据写入 fileChannel
			fileChannel.write(byteBuffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
