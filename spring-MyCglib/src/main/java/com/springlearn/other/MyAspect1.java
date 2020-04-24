package com.springlearn.other;

import com.springlearn.other.introduction.Dao;
import com.springlearn.other.introduction.OrderDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.aop.MyAspect
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-13 22:24
 * @version: 1.0
 **/
@Component
@Aspect("perthis(this(com.springlearn.other.IndexDaoImpl1))")
@Scope("prototype")
public class MyAspect1 {
	// SpringAop作用的最小单元是方法，方法的集合就称为连接点
	// 切点是连接点的集合
//	@Pointcut("execution(* com.springlearn.other.*.*(..))")
//	private void pointcut() {}

//	@Pointcut("within(com.springlearn.other.*)")
//	private void pointcut() {}

//	@Pointcut("this(java.lang.reflect.Proxy)")
	@Pointcut("this(com.springlearn.other.IndexDaoImpl1)")
//	@Pointcut("this(com.springlearn.other.IndexDaoImpl1)")
	private void pointThis() {}

//	@Pointcut("target(com.springlearn.other.IndexDaoImpl1)")
//	@Pointcut("target(com.springlearn.other.IndexDao)")
//	private void pointTarget() {}


//	@Pointcut("within(com.springlearn.other.*)")
//	private void pointAround() {}


	// 声明一个方法，作用于某一个pointcut上面
	@Before("pointThis()")
	public void doAccessCheck(JoinPoint joinPoint){
		System.out.println(this.hashCode());
//		System.out.println("getTarget:" + joinPoint.getTarget());
		System.out.println("-----aop before-----");
	}

//	@Around("pointAround()")
//	public void doAround(ProceedingJoinPoint proceedingJoinPoint){
//		System.out.println("-----before-----");
//		try {
//			Object[] args = proceedingJoinPoint.getArgs();
//			for (int i = 0; i < args.length; i++) {
//				args[i] += "world";
//			}
//			proceedingJoinPoint.proceed(args);
//		} catch (Throwable throwable) {
//			throwable.printStackTrace();
//		}
//		System.out.println("-----after-----");
//	}

	// 找到com.springlearn.other.*下的所有类，引入Dao这个接口的OrderDao这个实现类
//	@DeclareParents(value = "com.springlearn.other..*", defaultImpl = OrderDao.class)
//	public static Dao dao;
}
