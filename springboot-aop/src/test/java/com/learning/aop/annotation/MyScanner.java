package com.learning.aop.annotation;

import com.learning.aop.selector.MySelector;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MySelector.class)
public @interface MyScanner {
	@AliasFor("value")
	String[] basePackages() default {};

	@AliasFor("basePackages")
	String[] value() default {};
}
