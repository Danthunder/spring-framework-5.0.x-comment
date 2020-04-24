package com.spring.aop.dao;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @program: spring.OrderTabDaoImpl
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 00:43
 * @version: 1.0
 **/
//@Repository
public class OrderTabDaoAImpl implements OrderTabDao{


	@Override
	public void update(String sql) {
		System.out.println("A:" + sql);
	}

	@Override
	public void updateProduct(String sql) {

	}
}
