package com.springlearn.service;

import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.service.TestService
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-05 15:21
 * @version: 1.0
 **/

@Component
public class TestService {

	public TestService() {
		System.out.println("init TestService");
	}

	public void myFunc(String str) {
		System.out.println("testservice myfunc str" + str);
	}

}
