package com.springlearn.aopApp.test;

import com.springlearn.aopApp.dao.Dao;
import com.springlearn.aopApp.dao.DaoImpl1;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: spring.com.springlearn.aopApp.test.AopAppTest
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-27 00:31
 * @version: 1.0
 **/
public class AopAppTest {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext();
		ac.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
		ac.addBeanFactoryPostProcessor(new MyBeanDefinitionRegistryPostProcessor());
		ac.register(Appconfig.class);
		ac.refresh();
//		Dao dao = (Dao) ac.getBean("daoImpl1");
//		Dao dao = (Dao) ac.getBean(DaoImpl1.class);
//		Appconfig appconfig = (Appconfig) ac.getBean(Appconfig.class);
//		dao.query();
	}
}
