package com.springlearn.other;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * @program: spring.com.springlearn.other.IndexDaoImpl1
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-18 15:59
 * @version: 1.0
 **/
@Repository
//@Profile("dao2")
public class IndexDaoImpl2 implements IndexDao {

	public IndexDaoImpl2() {
		System.out.println("Construct dao2");
	}

	@Override
	public void test() {
		System.out.println("IndexDaoImpl2");
	}

	@Override
	public void test(String str) {

	}
}
