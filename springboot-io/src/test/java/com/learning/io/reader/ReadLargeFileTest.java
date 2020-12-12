package com.learning.io.reader;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReadLargeFileTest {

	@Test
	public void readLargeFile() throws IOException {
		ReadLargeFile rf = new ReadLargeFile();
		rf.readLargeFile();
	}
}