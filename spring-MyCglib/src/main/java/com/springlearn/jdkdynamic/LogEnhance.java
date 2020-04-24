package com.springlearn.jdkdynamic;

/**
 * @program: spring.com.springlearn.jdkdynamic.LogEnhance
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-21 19:19
 * @version: 1.0
 **/
public class LogEnhance implements UserDao{

	private UserDao userDao;

	public LogEnhance(UserDao userDao) {
		this.userDao = userDao;
	}

//	@Override
//	public void query() {
//		System.out.println("-----log------");
//		userDao.query();
//	}

	@Override
	public String query() {
		System.out.println("-----log------");
		userDao.query();
		return null;
	}

	@Override
	public void query(String str1, String str2) {

	}

	@Override
	public String queryReturn(String str) {
		return null;
	}
}
