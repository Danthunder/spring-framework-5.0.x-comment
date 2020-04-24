package com.springlearn.jdkdynamic;

import java.lang.reflect.Method;

/**
 * @program: spring.com.springlearn.jdkdynamic.UserDaoImpl
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-21 19:14
 * @version: 1.0
 **/
public class UserDaoImpl implements UserDao{

//	@Override
//	public void query() {
//		System.out.println("DB query");
//	}


	public String query() {
//		System.out.println("DB query");
		return "DB query";
	}

	@Override
	public void query(String str1, String str2) {
		System.out.println(str1 + str2);
	}

	@Override
	public String queryReturn(String str) {
		System.out.println("return " + str);
		return str;
	}


}
