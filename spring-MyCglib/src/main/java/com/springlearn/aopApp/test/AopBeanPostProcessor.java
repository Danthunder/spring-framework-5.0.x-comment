package com.springlearn.aopApp.test;

import com.springlearn.aopApp.dao.Dao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

/**
 * @program: spring.com.springlearn.aopApp.test.AopBeanPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 00:24
 * @version: 1.0
 **/
public class AopBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("daoImpl1".equals(beanName)) {
			Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),
					new Class[]{Dao.class},
					new MyInvocationHandler(bean));
			return proxy;
		}
		return bean;


	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}
}
