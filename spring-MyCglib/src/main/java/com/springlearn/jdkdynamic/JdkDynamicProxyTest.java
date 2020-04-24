package com.springlearn.jdkdynamic;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: spring.com.springlearn.jdkdynamic.JdkDynamicProxyTest
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-21 19:22
 * @version: 1.0
 **/
public class JdkDynamicProxyTest {
	public static void main(String[] args){

		UserDao target = new UserDaoImpl();
//		UserDao customProxy = new LogEnhance(target);
//		proxy.query();
		System.out.println("---------begin Custom Dynamic Proxy---------");

		/*手动模拟JDK动态代理：思路
		 *target对象---生成代理--->1.生成java文件--->2.编译生成class文件--->3.调用反射方式class.newInstance()--->得到代理对象
		 *
		 */
		long t1 = System.currentTimeMillis();
		UserDao customProxy = (UserDao) ProxyUtil.newInstance(
				target.getClass().getInterfaces()[0],
				new CustomInvocationHanderImpl(target));
		System.out.println(customProxy.query());
//		customProxy.query("hello", "world");
//		System.out.println(customProxy.queryReturn("hahaha"));
		long t2 = System.currentTimeMillis();

		System.out.println("---------begin JDK Dynamic Proxy---------");
		// JDK动态代理
		/*
		要生成代理类对象proxy，需要告诉它代理类要实现哪些接口（由interfaces指定）
		并且这些接口的代理逻辑（由new MyInvocationHandler(target)指定）
		 */
//		Class[] interfaces = new Class[]{UserDao.class};
//		UserDao jdkProxy = (UserDao) Proxy.newProxyInstance(
//				JdkDynamicProxyTest.class.getClassLoader(),
//				interfaces,
//				new MyInvocationHandler(target));
//		System.out.println(jdkProxy.query());
//		jdkProxy.query("hello", "world");
//		System.out.println(jdkProxy.queryReturn("hahaha"));
//		long t3 = System.currentTimeMillis();

//		System.out.println("---------Dynamic Proxy Comparision---------");
//		System.out.println("customProxy time:" + (t2-t1));
//		System.out.println("jdkProxy time:" + (t3-t2));
	}
}
