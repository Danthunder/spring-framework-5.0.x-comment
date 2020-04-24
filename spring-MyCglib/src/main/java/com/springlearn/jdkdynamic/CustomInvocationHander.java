package com.springlearn.jdkdynamic;

import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.jdkdynamic.CustomInvocationHander
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-22 13:41
 * @version: 1.0
 **/
public interface CustomInvocationHander {
	public Object invoke(Method m, Object... arg);
//	public Object invoke(Method m);
}
