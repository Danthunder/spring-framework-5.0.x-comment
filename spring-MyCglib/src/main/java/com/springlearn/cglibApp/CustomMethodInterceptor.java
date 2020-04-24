package com.springlearn.cglibApp;


import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.cglibApp.CustomMethodInterceptor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 19:31
 * @version: 1.0
 **/
public class CustomMethodInterceptor implements MethodInterceptor {

	/**
	 *
	 * @param o 代理对象
	 * @param method 目标对象中的方法
	 * @param objects 参数
	 * @param methodProxy 代理对象方法
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("CustomMethodInterceptor intercept");
		return methodProxy.invokeSuper(o,objects);

	}
}
