package com.spring.aop.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @program: spring.OrderTabDaoImpl
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 00:43
 * @version: 1.0
 **/
//@Repository
public class OrderTabDaoBImpl implements OrderTabDao{

	@Override
	public void update(String sql) {
		System.out.println("B:" + sql);
	}

	@Override
	public void updateProduct(String sql) {

	}
}
