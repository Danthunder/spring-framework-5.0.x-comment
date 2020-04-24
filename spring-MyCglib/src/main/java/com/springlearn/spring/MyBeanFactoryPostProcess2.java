package com.springlearn.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: spring.com.springlearn.spring.MyBeanFactoryPostProcess2
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-07 21:15
 * @version: 1.0
 **/
public class MyBeanFactoryPostProcess2 implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MyBeanPostProcessor.class);
		registry.registerBeanDefinition("myBeanPostProcessor",builder.getBeanDefinition());
	}
}
