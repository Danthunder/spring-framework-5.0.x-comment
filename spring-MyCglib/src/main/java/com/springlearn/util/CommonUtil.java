package com.springlearn.util;

import com.springlearn.myannotation.Entity;

/**
 * @program: spring.com.springlearn.util.CommonUtil
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-19 19:48
 * @version: 1.0
 **/
public class CommonUtil {

	public static String buildQuerySqlForEntity(Object object) {

		Class clazz = object.getClass();
		// 判断是否加了Entity注解
		if(clazz.isAnnotationPresent(Entity.class)) {
			// 加了Entity注解，则获取该注解
			Entity entity = (Entity) clazz.getAnnotation(Entity.class);
			String entityName = entity.value();
			System.out.println(entityName);
		}

		return "";
	}
}
