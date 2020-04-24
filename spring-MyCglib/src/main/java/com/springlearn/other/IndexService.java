package com.springlearn.other;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: spring.com.springlearn.other.IndexService
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-18 16:00
 * @version: 1.0
 **/
//@Service
public abstract class IndexService{

//	@Lookup(value = "indexDaoImpl")
//	public IndexDao getIndexDaoImplooo() {
//		return null;
//	}

//	@Lookup("indexDaoImpl")
//	protected abstract IndexDao createIndexDao();

	

	public void test() {
//		indexDaoImpl1.test();
		System.out.println("service"+this.hashCode());
//		System.out.println("dao"+indexDaoImpl1.hashCode());
//		System.out.println("dao"+getIndexDaoImplooo().hashCode());
//		System.out.println("dao"+createIndexDao().hashCode());
//		System.out.println("dao"+applicationContext.getBean("indexDaoImpl").hashCode());
	}


}
