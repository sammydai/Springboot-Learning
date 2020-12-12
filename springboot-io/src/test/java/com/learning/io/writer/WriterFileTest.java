package com.learning.io.writer;

import org.junit.Test;

public class WriterFileTest {

	@Test
	public void writeFileStream() {
		WriterFile.writeFileStream("/Users/daiwenting/Documents/TestCase/copytest.txt");
	}

	@Test
	public void writeFileWriter() {
		WriterFile.writeFileWriter("/Users/daiwenting/Documents/TestCase/copytest.txt");
	}

	@Test
	public void writeFileCopy() {
		WriterFile.writeFileCopy("/Users/daiwenting/Documents/lua/helloworld.lua",
				"/Users/daiwenting/Documents/TestCase/copytest.txt");
	}

	@Test
	public void consoleWriteToFile() {
		WriterFile.consoleWriteToFile("/Users/daiwenting/Documents/lua/Person.txt");
	}
}