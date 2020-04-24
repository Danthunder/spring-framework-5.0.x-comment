package com.springlearn.jdkdynamic;

/**
 * @program: spring.com.springlearn.jdkdynamic.UserDao
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-21 19:12
 * @version: 1.0
 **/
public interface UserDao {
//	public void query();

	public String query();

	public void query(String str1, String str2);

	public String queryReturn(String str);
}
