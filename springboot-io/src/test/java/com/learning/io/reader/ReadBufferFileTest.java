package com.learning.io.reader;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder()
@Slf4j
public class ReadBufferFileTest {

	ReadBufferFile rbf = new ReadBufferFile();

	@Before
	public void writeFile() throws IOException {
		rbf.writeFile();
	}

	/**
	 * 总共2m 55s
	 * @throws IOException
	 */
	@Test
	public void perByteOperation() throws IOException {
		rbf.perByteOperation();
	}

	@Test
	public void perByteOperationWith100Buffer() throws IOException {
		rbf.perByteOperationWith100Buffer();
	}
}