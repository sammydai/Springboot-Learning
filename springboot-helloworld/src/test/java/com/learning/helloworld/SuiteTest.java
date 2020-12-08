package com.learning.helloworld;

import com.learning.helloworld.bean.BeanMethodTest;
import com.learning.helloworld.compare.CompareMethodTest;
import com.learning.helloworld.optional.OptionalMethodTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Package: com.learning.helloworld
 * @Description: Suite Test
 * @Author: Sammy
 * @Date: 2020/12/8 12:35
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({BeanMethodTest.class, CompareMethodTest.class, OptionalMethodTest.class})
public class SuiteTest {
	/*
     * 测试套件就是组织测试类一起运行的
     * 写一个作为测试套件的入口类，这个类里不需要包含其他的方法
     * 1.更改测试运行器Suite.class
     * 2.将要测试的类作为数组传入到Suite.SuiteClasses（{}）
     */
}
