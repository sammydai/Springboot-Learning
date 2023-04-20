package com.learning.apidesign.apiversion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.learning.apidesign.apiversion
 * @Description: APIVersion
 * @Author: Sammy
 * @Date: 2020/12/22 09:08
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface APIVersion {
	String[] value();
}
