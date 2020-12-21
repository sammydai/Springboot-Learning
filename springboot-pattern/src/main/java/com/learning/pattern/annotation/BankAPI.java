package com.learning.pattern.annotation;

import java.lang.annotation.*;

/**
 * @Package: com.learning.pattern.domain
 * @Description: BankAPI
 * @Author: Sammy
 * @Date: 2020/12/20 23:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
@Inherited
public @interface BankAPI {
	String desc() default "";
	String url() default "";
}

