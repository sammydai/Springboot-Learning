package com.learning.aop.annotation;

import com.learning.aop.selector.CustomizedAopImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomizedAopImportSelector.class)
public @interface EnableAop {
}
