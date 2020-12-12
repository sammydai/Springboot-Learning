package com.learning.io.reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReaderFileTest {

	@Test
	public void readFileStream() {
		ReaderFile.readFileStream("/Users/daiwenting/Documents/TestCase/Person.txt");
	}

	@Test
	public void readFileReader() {
		ReaderFile.readFileReader("/Users/daiwenting/Documents/TestCase/cloudHttp.txt");
	}

	@Test
	public void readFileStreamReader() {
		ReaderFile.readFileStreamReader("/Users/daiwenting/Documents/TestCase/lua/test.txt");
	}

	@Test
	public void readFileBuffer() {
		ReaderFile.readFileBuffer("/Users/daiwenting/Documents/TestCase/cloudHttp.txt");
	}

}