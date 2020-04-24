package com.springlearn.aopApp.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @program: spring.com.springlearn.aopApp.test.MyBeanFactoryPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-28 00:44
 * @version: 1.0
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor postProcessBeanFactory");
	}
}
