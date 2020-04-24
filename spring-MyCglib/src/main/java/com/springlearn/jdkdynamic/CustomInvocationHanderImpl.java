package com.springlearn.jdkdynamic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.jdkdynamic.CustomInvocationHanderImpl
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-22 13:42
 * @version: 1.0
 **/
public class CustomInvocationHanderImpl implements CustomInvocationHander{

	private Object targetObject;

	public CustomInvocationHanderImpl(Object targetObject) {
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(Method m, Object... args) {

		try {
			if (m.getAnnotation(CustomBefore.class)!= null) {

			}
			return m.invoke(targetObject, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(m.getAnnotation(CustomAfter.class)!= null) {

			}
		}

		return null;
	}

//	@Override
//	public Object invoke(Method m) {
//		System.out.println("CustomInvocationHanderImpl");
//		try {
//			return m.invoke(targetObject);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
