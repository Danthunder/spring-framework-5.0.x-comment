package com.spring.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: spring.com.spring.autowired.TestAutowired
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-02-01 14:10
 * @version: 1.0
 **/
public class TestAutowired {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
		TestDao testDao = (TestDao) ac.getBean("testDao");
		testDao.query();
	}
}
