package com.dwt.springbootzookeeperkey.anotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ZooLock {
	String key();

	long timeout() default 5*1000;

	TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
