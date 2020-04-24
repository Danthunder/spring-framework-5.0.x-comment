package com.springlearn.aopApp.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.aopApp.test.MyInvocationHandler
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 00:22
 * @version: 1.0
 **/
public class MyInvocationHandler implements InvocationHandler {

	private Object targetObject;

	public MyInvocationHandler(Object targetObject) {
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我是代理对象");
		return method.invoke(targetObject, args);
	}
}
