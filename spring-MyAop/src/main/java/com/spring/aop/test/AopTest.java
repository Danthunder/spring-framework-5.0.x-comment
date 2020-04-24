package com.spring.aop.test;

import com.spring.aop.app.Appconfig;
import com.spring.aop.dao.OrderTabDao;
import com.spring.aop.service.OrderService;
import com.spring.aop.service.Service;
import com.spring.aop.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: spring.com.spring.aop.test.AopTest
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-30 00:58
 * @version: 1.0
 **/
public class AopTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
//		OrderTabDao obj = (OrderTabDao) ac.getBean("userService");
//		OrderService orderService = ac.getBean(OrderService.class);
//		orderService.query("B");
//		OrderTabDao orderTabDaoImpl = ac.getBean(OrderTabDao.class);
//		orderTabDaoImpl.update("");

		System.out.println(ac.containsBean("myAspect"));
		Service service = (Service) ac.getBean("userService");
		service.query();
	}
}
