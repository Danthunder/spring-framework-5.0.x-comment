package com.springlearn.aopApp.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.aopApp.test.MyBeanDefinitionRegistryPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-28 00:40
 * @version: 1.0
 **/
@Component
public class MyBeanDefinitionRegistryPostProcessorAnno implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessorAnno postProcessBeanDefinitionRegistry");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessorAnno postProcessBeanFactory");
	}
}
