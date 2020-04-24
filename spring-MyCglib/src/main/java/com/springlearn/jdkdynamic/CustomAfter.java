package com.springlearn.jdkdynamic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: spring.com.springlearn.jdkdynamic.CustomBefore
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-22 20:17
 * @version: 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAfter {
	String value();
}
