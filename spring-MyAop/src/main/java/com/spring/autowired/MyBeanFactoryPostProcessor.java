package com.spring.autowired;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.spring.autowired.MyBeanFactoryPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-02-01 14:14
 * @version: 1.0
 **/
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition testDaoBD = (GenericBeanDefinition) beanFactory.getBeanDefinition("testDao");
		testDaoBD.setAutowireMode(2);
}
}
