package com.springlearn.mybatisApp.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @program: spring.com.springlearn.mybatisApp.CardDao
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-25 22:59
 * @version: 1.0
 **/
public interface CardDao {

	@Select("select * from user where name like '%${name}%'")
	public List<Map<Integer, String>> list(@Param("name") String name);
}
