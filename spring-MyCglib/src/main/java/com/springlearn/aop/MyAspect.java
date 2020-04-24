package com.springlearn.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.aop.MyAspect
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-13 22:24
 * @version: 1.0
 **/
@Component
@Aspect
public class MyAspect {
	// SpringAop作用的最小单元是方法，方法的集合就称为连接点
	// 切点是连接点的集合
	@Pointcut("execution(* com.springlearn.other.*.*(..))")
	private void pointcut() {}

//	@Pointcut("within(com.springlearn.other.*)")
//	private void pointcut() {}

	// 声明一个方法，作用于某一个pointcut上面
	@Before("pointcut()")
	public void doAccessCheck(){
		System.out.println("-----aop before-----");
	}
}
