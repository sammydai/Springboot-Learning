package com.learning.io.reader;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @Package: com.learning.io.reader
 * @Description: ReaderFile
 * @Author: Sammy
 * @Date: 2020/11/19 13:11
 */

public class ReaderFile {
	public static void main(String[] args) {
		// readFileStream("/Users/daiwenting/Documents/TestCase/Person.txt");
		// readFileReader("/Users/daiwenting/Documents/TestCase/cloudHttp.txt");
		// readFileStreamReader("/Users/daiwenting/Documents/lua/test.txt");
		readFileBuffer("/Users/daiwenting/Documents/TestCase/cloudHttp.txt");
	}

	/**
	 * 字节输入流 InputStream
	 * 构造字节数组
	 * read(byte)
	 * 构造string
	 * @param filename
	 */
	public static void readFileStream(String filename) {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(filename));
			byte[] bytes = new byte[1024];
			int len;
			while ((len =is.read(bytes))!= -1) {
				System.out.println(new String(bytes, 0, len,Charset.forName("UTF-8")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 字符输入流
	 * @param filename
	 */
	public static void readFileReader(String filename) {
		FileReader fr = null;
		try {
			fr = new FileReader(new File(filename));
			int len;
			char[] chars = new char[20];
			while ((len = fr.read(chars)) != -1) {
				System.out.println(new String(chars, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void readFileStreamReader(String filename) {
		InputStreamReader isr = null;
		try {
			isr =new InputStreamReader(new FileInputStream(new File(filename)),Charset.forName("UTF-8"));
			char[] chars = new char[1024];
			int len;
			while ((len = isr.read(chars)) != -1) {
				System.out.println(new String(chars, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从一个文件中，一行一行的读取出所有内容，然后打印在控制台上。
	 * 使用FileBuffer
	 * @param filename
	 */
	public static void readFileBuffer(String filename) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename)), Charset.forName("UTF-8")));
			String buff = null;
			while ((buff = bufferedReader.readLine()) != null) {
				System.out.println(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
