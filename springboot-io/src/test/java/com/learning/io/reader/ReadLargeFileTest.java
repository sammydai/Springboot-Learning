package com.learning.io.reader;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReadLargeFileTest {
	ReadLargeFile rf = new ReadLargeFile();

	@Test
	public void readLargeFile() throws IOException {
		rf.readLargeFile();
	}


	@Test
	public void generateFile() throws IOException {
		rf.generateFile();
	}

	/**
	 * java.nio.file.FileSystemException: demo.txt: Too many open files
	 * @throws IOException
	 */
	@Test
	public void fileDescription() throws IOException {
		rf.fileDescription();
	}

	@Test
	public void fileDescriptionSource() throws IOException {
		rf.fileDescriptionSource();
	}
}