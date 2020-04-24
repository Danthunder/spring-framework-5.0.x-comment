package com.springlearn.myannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: spring.com.springlearn.myannotation.Entity
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-19 19:40
 * @version: 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {

	public String value() default "";

}
