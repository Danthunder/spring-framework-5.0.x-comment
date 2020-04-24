package com.springlearn.other.introduction;

import org.springframework.stereotype.Repository;

/**
 * @program: spring.com.springlearn.other.introduction.OrderDao
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-20 13:42
 * @version: 1.0
 **/
@Repository
public class OrderDao implements Dao{

	@Override
	public void query() {
		System.out.println("orderDao query");
	}

	@Override
	public void insert() {
		System.out.println("orderDao insert");

	}

}
