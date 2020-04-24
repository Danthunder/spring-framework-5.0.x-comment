package com.springlearn.app;

import com.springlearn.spring.MyBeanFactoryPostProcess2;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @program: spring.com.springlearn.app.EnableMyAop
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-07 21:27
 * @version: 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
//@Import(MyBeanFactoryPostProcess2.class)
public @interface EnableMyAop {
}
