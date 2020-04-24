package com.springlearn.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.jdkdynamic.MyInvocationHandler
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-22 10:44
 * @version: 1.0
 **/
public class MyInvocationHandler implements InvocationHandler {

	private Object targetObject;

	public MyInvocationHandler(Object targetObject) {
		this.targetObject = targetObject;
	}

	/**
	 *
	 * @param proxy 代理类中的代理对象com.sun.proxy.$Proxy0
	 * @param method 代理实例上调用的接口方法的方法对象，严重注意不是目标对象中的目标方法(method对象)
	 * @param args 代理实例上调用的接口方法的方法参数
	 * @return
	 * @throws Throwable
	 *
	 * invoke方法要完成
	 * 1. proxy logic execute
	 * 2. target logic execute
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy logic start");
		/*
		method.invoke(obj args)需要传入两个参数
		obj: 目标对象（需要通过构造方法传入）
		args：目标方法传入的参数，通过InvocationHandler对象中获取
		 */
		Object returnObj = method.invoke(targetObject, args);
		System.out.println("proxy logic end");
		return returnObj;
	}
}
