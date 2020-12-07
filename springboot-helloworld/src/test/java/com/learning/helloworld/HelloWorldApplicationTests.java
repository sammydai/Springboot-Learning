package com.learning.helloworld;

import com.learning.helloworld.domain.People;
import com.learning.helloworld.utils.ApplicationContextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {

	@Autowired(required = false)//required=false 的意思就是允许当前的Bean对象为null。
	private People people;

	@Test
    public void test() {
        System.out.println("= = = = = = = = = = = = = ");
        System.out.println("people = " + people);
        System.out.println("= = = = = = = = = = = = = ");
        People bean = ApplicationContextUtils.getBean(People.class);

    }
}
