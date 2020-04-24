package com.springlearn.entity;

import com.springlearn.myannotation.Entity;

/**
 * @program: spring.com.springlearn.entity.CityEntity
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-19 19:55
 * @version: 1.0
 **/
@Entity("city")
public class CityEntity {

	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
