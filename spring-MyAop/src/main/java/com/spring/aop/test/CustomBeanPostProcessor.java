package com.spring.aop.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.spring.aop.test.CustomBeanPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 14:27
 * @version: 1.0
 **/
//@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}
}
