package com.springlearn.dao;

import com.springlearn.myannotation.Entity;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.dao.MyDao
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-13 23:07
 * @version: 1.0
 **/
@Component
public class CityDao {

	public CityDao() {
		System.out.println("CityDao init");
	}

	public void query() {
		System.out.println("exe CityDao query()");
	}


}
