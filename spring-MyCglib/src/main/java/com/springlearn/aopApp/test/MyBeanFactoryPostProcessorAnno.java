package com.springlearn.aopApp.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.aopApp.test.MyBeanFactoryPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-28 00:44
 * @version: 1.0
 **/
@Component
public class MyBeanFactoryPostProcessorAnno implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessorAnno postProcessBeanFactory");
	}
}
