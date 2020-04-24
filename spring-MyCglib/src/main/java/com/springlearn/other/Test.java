package com.springlearn.other;

import com.springlearn.dao.CityDao;
import com.springlearn.entity.CityEntity;
import com.springlearn.other.introduction.Dao;
import com.springlearn.other.introduction.MyDao;
import com.springlearn.other.introduction.TestDao;
import com.springlearn.util.CommonUtil;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @program: spring.com.springlearn.other.Test
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-18 16:01
 * @version: 1.0
 **/
public class Test {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext();

//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//		ac.getEnvironment().setActiveProfiles("dao2");
//		ac.register(Spring.class);
//		ac.refresh();
//		System.out.println(ac.getBean(IndexDaoImpl2.class));
//		System.out.println(ac.getBean(IndexDaoImpl1.class));

//		CityEntity cityEntity = new CityEntity();
//		cityEntity.setId("1");
//		cityEntity.setName("Beijing");
//		String buildSql = CommonUtil.buildQuerySqlForEntity(cityEntity);
//		System.out.println(buildSql);

//		IndexDaoImpl1 dao = (IndexDaoImpl1) ac.getBean("indexDaoImpl");
		// dao是代理对象
		ac.register(IndexDaoImpl1.class);
		IndexDao dao = (IndexDao) ac.getBean("indexDaoImpl");
//		IndexDao dao2 = (IndexDao) ac.getBean("indexDaoImpl");
//		System.out.println(dao instanceof IndexDaoImpl1);
//		System.out.println(dao instanceof IndexDao);
//		System.out.println(dao instanceof Proxy);
//		dao.test();
//		dao2.test("hello");


//		Dao myDao = (Dao) ac.getBean(MyDao.class);
//		myDao.insert();
//		myDao.query();

//		Proxy.newProxyInstance()
	}
}
