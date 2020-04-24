package com.springlearn.spring;

import com.springlearn.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.spring.MyBeanFactoryPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-05 15:15
 * @version: 1.0
 **/
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		/*
		ConfigurableListableBeanFactory是DefaultListableBeanFactory的父类，传子类给它，可以用父类接收。 可以向下转型
		通过beanDefinitionMap的key（bean name），获取某个beanDefinition，从而对其beanDefinition进行修改
		 */
		System.out.println("execute MyBeanFactoryPostProcessor.");
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("cityService");
		genericBeanDefinition.setBeanClass(TestService.class);

	}
}
