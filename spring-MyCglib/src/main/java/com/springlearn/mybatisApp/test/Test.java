package com.springlearn.mybatisApp.test;

import com.springlearn.mybatisApp.app.Appconfig;
import com.springlearn.mybatisApp.dao.CardDao;
import com.springlearn.mybatisApp.service.CardService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @program: spring.com.springlearn.mybatisApp.test.Test
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-25 23:07
 * @version: 1.0
 **/
public class Test {
	public static void main(String[] args) {


		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
//		CardDao cardDao = (CardDao) ac.getBean("cardDao");
//		cardDao.list("li");

		CardService cardService = (CardService) ac.getBean("cardService");
		cardService.list();
//		System.out.println(ac.getBean("cardDao"));
//		ac.getBean(CardService.class).list();

	}
}
