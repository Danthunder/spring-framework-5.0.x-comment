package com.spring.aop.test;

import com.spring.aop.dao.OrderTabDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Wang danning
 * @create: 2020-02-04 20:47
 **/
//@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor, InvocationHandler {

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

		if(beanName.equals("userService")) {
			return Proxy.newProxyInstance(this.getClass().getClassLoader(),
					new Class[]{OrderTabDao.class},
					this);
		}
		return null;

	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if(beanName.equals("userService")) {
			System.out.println("postProcessAfterInstantiation");
		}
		return true;
	}


	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		return null;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy");
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("userService")) {
			System.out.println("postProcessAfterInitialization。。。");
		}

		return null;
	}
}
