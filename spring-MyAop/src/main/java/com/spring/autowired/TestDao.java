package com.spring.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: spring.com.spring.autowired.TestDao
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-02-01 14:06
 * @version: 1.0
 **/
@Component
public class TestDao {

//	@Autowired
//	private TestService1 testServiceInterface;

//	@Resource
//	private TestServiceInterface testService1;
//	private TestService testService;
//	private TestService1 testService1;

//	public TestDao(TestService1 testServiceInterface) {
//		System.out.println("construct TestDao:" + testServiceInterface);
//		this.testServiceInterface = testServiceInterface;
//	}

//	@Autowired
	public void setTestService1(TestService1 testServiceInterfaceX) {
//		this.testServiceInterface = testServiceInterfaceX;
		System.out.println("setTestService1:" + testServiceInterfaceX);
	}

	public void setA(){
		System.out.println("---------");
	}
	public void getA(){

	}
//	public void setTestService(TestServiceInterface testServiceInterfaceX) {
//		this.testServiceInterface = testServiceInterfaceX;
//		System.out.println("setTestService:" + testServiceInterface);
//	}

	public void query(){
//		System.out.println("TestServiceInterface：" + testServiceInterface);
	}
}
