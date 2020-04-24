package com.springlearn.factorybean;

import com.springlearn.other.IndexDaoImpl1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: spring.com.springlearn.factorybean.Test
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-23 16:43
 * @version: 1.0
 **/
public class Test {
	public static void main(String[] args) {
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
//		DaoFactoryBean daoFactoryBean = (DaoFactoryBean) ac.getBean("&daoFactoryBean");
//		daoFactoryBean.testBean();
//		TempDaoFactoryBean tempDaoFactoryBean = (TempDaoFactoryBean) ac.getBean("daoFactoryBean");
//		tempDaoFactoryBean.test();

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
//		annotationConfigApplicationContext.getEnvironment().setActiveProfiles("formal");
//		annotationConfigApplicationContext.register(Appconfig.class);
		annotationConfigApplicationContext.scan("com.springlearn.other");
		annotationConfigApplicationContext.refresh();
		annotationConfigApplicationContext.getBean(IndexDaoImpl1.class);





	}
}
