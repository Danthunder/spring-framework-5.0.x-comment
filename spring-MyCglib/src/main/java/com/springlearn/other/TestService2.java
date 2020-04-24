package com.springlearn.other;

import com.springlearn.other.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @program: spring.com.springlearn.other.exclude.TestService2
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-18 23:02
 * @version: 1.0
 **/
//@Service
//@Scope("prototype")
public class TestService2 {

	@Autowired
	TestService testService;
}
