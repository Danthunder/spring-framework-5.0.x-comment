package com.springlearn.aopApp.test;

import com.springlearn.aopApp.anno.EnableMyAop;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @program: spring.com.springlearn.aopApp.test.Appconfig
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 00:28
 * @version: 1.0
 **/
@Configuration
@ComponentScan("com.springlearn.aopApp")
//@EnableMyAop
@Import({AopImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class Appconfig {
}
