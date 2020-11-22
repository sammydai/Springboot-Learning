package com.learning.io.writer;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @Package: com.learning.io.writer
 * @Description: IODemo
 * @Author: Sammy
 * @Date: 2020/11/19 12:39
 */

public class WriterFile {

	public static void main(String[] args) {
		// consoleWriteToFile("/Users/daiwenting/Documents/lua/Person.txt");
		// new IODemo().writeToFile("/Users/daiwenting/Documents/lua");
		// writeFileCopy("/Users/daiwenting/Documents/lua/helloworld.lua",
		// 		"/Users/daiwenting/Documents/TestCase/copytest.txt");
		//writeFileStream("/Users/daiwenting/Documents/TestCase/copytest.txt");
		writeFileWriter("/Users/daiwenting/Documents/TestCase/copytest.txt");
	}
	/**
	 * 已知一个文件路径“C:\ProgramData\Kugou\2.txt”，截取文件名称，
	 * 然后在D:\a下创建同名的文件。
	 * 然后使用键盘输入一首诗，保存在该文件中。
	 * 1 创建新文件需要加入分隔符
	 * @param filename
	 */
	public static void consoleWriteToFile(String filename) {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		File file = new File(filename);
		if (file.isFile()) {
			String name = file.getName();
			File newFile = new File("/Users/daiwenting/Documents/TestCase" + File.separator + name);
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile,true)));
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					if (("exit").equals(str)) {
						System.exit(-1);
					}
					bufferedWriter.write(str);
					bufferedWriter.newLine();
					bufferedWriter.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
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
	}

	public static void writeFileStream(String filename) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filename),true);
			// String str = "JVM类加载机制详解（一）JVM类加载过";
			String str = "System.out.println(\"请输入a的文件名：\");//\"C:\\\\Users\\\\Desktop\\\\a.txt\"";
			fos.write(str.getBytes(Charset.forName("UTF-8")));
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

	//TODO: 换一行写
	public static void writeFileWriter(String filename) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File(filename), true);
			fileWriter.write("sss读取一个文本文件，统");
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeFileCopy(String srcfile, String destfile) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(srcfile))));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(destfile),true)));
			String bff = null;
			while ((bff = br.readLine()) != null) {
				bw.write(bff);
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
