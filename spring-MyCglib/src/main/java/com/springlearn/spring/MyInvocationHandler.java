package com.springlearn.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.spring.MyInvocationHandler
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-07 20:23
 * @version: 1.0
 **/
public class MyInvocationHandler implements InvocationHandler {

	private Object o;

	public MyInvocationHandler (Object o){
		this.o = o;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("---------AOP---------");

		return method.invoke(o,args);
	}
}
