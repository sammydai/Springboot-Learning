package com.learning.pattern.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
	int order() default -1;

	int length() default -1;

	String type() default "";
}
