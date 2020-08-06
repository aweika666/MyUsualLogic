package com.demo.starter.annotation;

import java.lang.annotation.*;

/**
 * demo注解
 * 
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DemoAnnotation {
	String message() default "";
}
