package com.springlearn.aopApp.dao;

import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.aopApp.dao.DaoImpl1
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 00:20
 * @version: 1.0
 **/
@Component
public class DaoImpl1 implements Dao{

	@Override
	public void query() {
		System.out.println("daoImpl1");
	}
}
