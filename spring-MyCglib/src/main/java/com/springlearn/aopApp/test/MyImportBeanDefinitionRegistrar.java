package com.springlearn.aopApp.test;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: spring.com.springlearn.aopApp.test.MyImportBeanDefinitionRegistrar
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 16:21
 * @version: 1.0
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("MyImportBeanDefinitionRegistrar");
	}
}
