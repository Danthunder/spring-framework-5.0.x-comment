package com.springlearn.aopApp.anno;

import com.springlearn.aopApp.test.AopBeanPostProcessor;
import com.springlearn.aopApp.test.AopImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: spring.com.springlearn.aopApp.anno.EnableMyAop
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 00:28
 * @version: 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AopImportSelector.class)
public @interface EnableMyAop {

}
