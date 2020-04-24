package com.springlearn.mybatisApp.test;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.FactoryBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: spring.com.springlearn.mybatisApp.test.CustomFactoryBean
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-26 14:57
 * @version: 1.0
 **/

public class CustomFactoryBean implements FactoryBean, InvocationHandler {

	private Class clazz; // 需要通过JDK动态代理，生成代理对象的接口

	public CustomFactoryBean(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object getObject() throws Exception {

		Class[] classes = new Class[]{clazz};
		Object proxyObject = Proxy.newProxyInstance(this.getClass().getClassLoader(),
				classes,
				this);

		return proxyObject;
	}

	@Override
	public Class<?> getObjectType() {
		return clazz;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		Method myMethod = proxy.getClass().getInterfaces()[0]
//				.getMethod(method.getName(),method.getParameterTypes());
//		Select select = myMethod.getAnnotation(Select.class);
//		String sql = select.value()[0];

		Select select = method.getAnnotation(Select.class);
		String sql = select.value()[0];


		System.out.println(sql);
		return null;
	}
}
