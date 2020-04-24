package com.spring.aop.service;

import com.spring.aop.dao.OrderTabDao;
import com.spring.aop.dao.OrderTabDaoAImpl;
import com.spring.aop.dao.OrderTabDaoBImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: spring.com.spring.aop.service.OrderService
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 01:13
 * @version: 1.0
 **/
//@Service
public class OrderService implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private OrderTabDao orderTabDao;

	@Autowired
	private Map<String, OrderTabDao> orderTabDaoMap;

	public void query(String userName) {
		if(userName.equals("A")) {
			orderTabDao = orderTabDaoMap.get("orderTabDaoAImpl");
//			orderTabDao = (OrderTabDao) applicationContext.getBean("orderTabDaoAImpl");
			orderTabDao.update("aaa");
		}
		else if (userName.equals("B")){
			orderTabDao = orderTabDaoMap.get("orderTabDaoBImpl");
//			orderTabDao = (OrderTabDao) applicationContext.getBean("orderTabDaoBImpl");
			orderTabDao.update("bbb");
		}
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
