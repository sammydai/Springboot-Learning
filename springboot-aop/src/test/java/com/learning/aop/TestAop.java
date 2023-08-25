package com.learning.aop;

import com.learning.aop.annotation.MyAfter;
import com.learning.aop.annotation.MyAround;
import com.learning.aop.annotation.MyAspect;
import com.learning.aop.annotation.MyBefore;
import org.springframework.stereotype.Component;

/**
 * [一句话描述该类的功能]
 *
 * @author : [Sammy]
 * @version : [v1.0]
 * @createTime : [2023/8/23 16:44]
 */
@Component
@MyAspect
public class TestAop {
	@MyBefore("com.learning.aop.service")
	public void testBefore() throws Throwable {
		System.out.println("before   -----------------");
	}

	@MyAfter("com.learning.aop.service")
	public void testAfter() {
		System.out.println("after   ------------------");
	}

	@MyAround("com.learning.aop.service")
	public void testAround() throws Throwable {
		System.out.println("around   -----------------");
	}
}

