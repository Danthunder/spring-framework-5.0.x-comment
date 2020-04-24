package com.spring.aop.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: spring.aop.test.MyAspect
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 00:49
 * @version: 1.0
 **/
@Component
@Aspect
public class MyAspect {

	@Pointcut("execution(* com.spring.aop.service..*.*(..))")
	public void pointcut(){}

	@Before("pointcut()")
	public void before() {
		System.out.println("proxy before");
	}

}
