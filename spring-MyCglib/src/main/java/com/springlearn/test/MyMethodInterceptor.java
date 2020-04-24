package com.springlearn.test;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.test.MyMethodInterceptor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-15 00:11
 * @version: 1.0
 **/
public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("----cglib intercept----");
		return methodProxy.invokeSuper(o, objects);
	}
}
