package com.dwt.springbootanotation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAnotationApplicationTests {

	// @Resource(name="namedFile")
	@Resource
	@Autowired
    private File defaultFile;

	@Test
	public void test1() {
		assertNotNull(defaultFile);
        Assert.assertEquals("namedFile.txt",defaultFile.getName());
	}

}
