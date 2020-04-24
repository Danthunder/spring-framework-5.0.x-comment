package com.springlearn.jdkdynamic;

/**
 * @program: spring.com.springlearn.jdkdynamic.CustomAspect
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-22 20:20
 * @version: 1.0
 **/
public class CustomAspect {

	@CustomPointCut("com.springlearn.jdkdynamic.UserDaoImpl")
	public void pointCut(){}

	@CustomBefore("pointCut()")
	public void beforeAdvice() {
		System.out.println("CustomAspect beforeAdvice");
	}

	@CustomAfter("pointCut()")
	public void afterAdvice() {
		System.out.println("CustomAspect afterAdvice");
	}
}
