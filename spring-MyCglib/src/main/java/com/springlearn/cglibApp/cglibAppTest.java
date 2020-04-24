package com.springlearn.cglibApp;

import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: spring.com.springlearn.cglibApp.cglibAppTest
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 19:30
 * @version: 1.0
 **/
public class cglibAppTest {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
		ac.getBean("indexDao");
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(IndexDao.class);
		enhancer.setUseFactory(false);
		// 利用spring的命名规则生成名字
		enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
		// 过滤方法（方法拦截器），不能每次都去new

		enhancer.setCallback(new CustomMethodInterceptor());
		IndexDao indexDao = (IndexDao) enhancer.create();
		indexDao.query();
//		enhancer.setCallbackFilter(CALLBACK_FILTER);
//		enhancer.setCallbackTypes(CALLBACK_FILTER.getCallbackTypes());
//		return enhancer;

	}
}
