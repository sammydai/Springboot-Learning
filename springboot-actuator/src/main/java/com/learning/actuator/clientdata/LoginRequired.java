package com.learning.actuator.clientdata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.learning.actuator.clientdata
 * @Description: LoginRequired
 * @Author: Sammy
 * @Date: 2020/12/23 10:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface LoginRequired {
	String sessionKey() default "currentUser";
}
