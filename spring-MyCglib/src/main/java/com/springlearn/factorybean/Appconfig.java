package com.springlearn.factorybean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: spring.com.springlearn.factorybean.Appconfig
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-23 16:43
 * @version: 1.0
 **/
@ComponentScan("com.springlearn.factorybean")
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Appconfig {

}
