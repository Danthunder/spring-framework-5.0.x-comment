package com.springlearn.mybatisApp.anno;

import com.springlearn.mybatisApp.test.CustomImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: spring.com.springlearn.mybatisApp.anno.MyMapperScan
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-26 17:15
 * @version: 1.0
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomImportBeanDefinitionRegistrar.class)
public @interface MyMapperScan {
	String[] value();
}
