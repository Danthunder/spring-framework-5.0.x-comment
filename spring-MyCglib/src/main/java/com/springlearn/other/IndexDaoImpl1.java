package com.springlearn.other;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @program: spring.com.springlearn.other.IndexDaoImpl1
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-18 15:59
 * @version: 1.0
 **/
//@Repository("indexDaoImpl")
//@Scope("prototype")
//@Profile("dao1") // 使用@Profile注解的类会在满足条件时将其实例化（放入BD中）
public class IndexDaoImpl1 implements IndexDao {
	@Override
	public void test() {}

	@Override
	public void test(String str) {
		System.out.println("IndexDaoImpl1");
		System.out.println(str);
	}

	public IndexDaoImpl1() {
		System.out.println("construct dao1");
	}

//	@PostConstruct
//	public void init() {
//		System.out.println("init dao1");
//	}
}
