package com.springlearn.aop;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.aop.BeforeAdvice
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-10 23:21
 * @version: 1.0
 **/
//@Component
@Aspect
public class BeforeAdvice {

	@Pointcut(value="execution(* com.springlearn..*(..))") // the pointcut expression
	private void pointCut(){ // the pointcut signature:定义一个切入点 后面的通知直接引入切入点方法pointCut即可            personServerImpl下面的所有方法
	}

	@Before(value = "pointCut()")
	public void methodBefore() {
		System.out.println("beforeAdvice methodBefore...");
	}
//
//	@AfterReturning(
//			value = "pointCut()",
//			returning = "retValue"
//	)
//	public void methodAfterReturning(Object retValue) {
//		System.out.println("exe after returning, return value " + retValue);
//	}
//
//	@AfterThrowing(
//			value = "pointCut()",
//			throwing = "ex"
//	)
//	public void methodAfterThrowing(Exception ex) {
//		System.out.println("after throwing func exe..." + ex.getMessage());
//	}

//	@Around(value = "pointCut()")
//	public Object methodAround(ProceedingJoinPoint pjp) throws Throwable {
//		Object ret = pjp.proceed();
//		System.out.println(pjp.getSignature().getName());
//		return ret;
//	}
}
