package com.springlearn.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @program: spring.com.springlearn.other.TestService
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-18 23:01
 * @version: 1.0
 **/
//@Service
//@Scope("prototype")
public class TestService {

	@Autowired
	TestService2 testService2;
}
