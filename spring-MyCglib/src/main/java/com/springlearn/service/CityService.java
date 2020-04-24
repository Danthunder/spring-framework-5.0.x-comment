package com.springlearn.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @program: spring.com.springlearn.service.CityService
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-05 12:33
 * @version: 1.0
 **/
@Component
public class CityService implements City {

//	@PostConstruct
//	public void init() {
//		System.out.println("lifecycle callback init");
//	}

	public CityService() {
		System.out.println("init city service");
	}

	public void myAOP() {
		System.out.println("------------AOP------------");
	}


	@Override
	public void afterPropertiesSet() {
		System.out.println("afterPropertiesSet method...");
	}

	@Override
	public String testFunc(String str) throws Exception {

		System.out.println("执行city Service的testFunc()，参数：" + str);

//		int a = 1/0;
		return str;
	}
}
