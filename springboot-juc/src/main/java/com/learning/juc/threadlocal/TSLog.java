package com.learning.juc.threadlocal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TSLog
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/9/25 14:54]
 */

public class TSLog {
	private PrintWriter writer = null;

	public TSLog(String fileName) {
		try {
			this.writer = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			//
		}
	}

	public void println(String s) {
		writer.println(s);
	}

	public void close() {
		writer.println(" ==== end of log ==== ");
		writer.close();
	}
}

