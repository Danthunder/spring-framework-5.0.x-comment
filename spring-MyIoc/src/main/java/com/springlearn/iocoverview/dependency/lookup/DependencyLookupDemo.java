package com.springlearn.iocoverview.dependency.lookup;

import com.springlearn.iocoverview.annotation.Super;
import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 * 1. 按照名称查找
 * 2. 按照类型来查找
 *
 * @author Wang danning
 * @since 2020-04-07 22:37
 **/
public class DependencyLookupDemo {

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

//		lookupInRealTime(beanFactory);
//		lookupInLazy(beanFactory);

		// 按照类型，查找单一对象
		lookupByType(beanFactory);
		// 按照类型，查找集合对象
		lookupCollectionByType(beanFactory);

		// 通过注解查找
		lookupByAnnotationType(beanFactory);

	}

	private static void lookupByAnnotationType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
			System.out.println("按照类型查找标注@Super的对象：" + users);
		}
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("按照类型查找所有对象：" + users);
		}
	}

	private static void lookupByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		System.out.println("按照类型查找单一对象：" + user);
	}

	private static void lookupInRealTime(BeanFactory beanFactory) {
		User user = (User) beanFactory.getBean("user");
		System.out.println("实时查找：" + user);
	}

	private static void lookupInLazy(BeanFactory beanFactory) {
		// ObjectFactory没有生成新的bean，但是FactoryBean则完全不一样，其调用getObject是得到新的spring bean
		ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
		User user = objectFactory.getObject();
		System.out.println("延时查找：" + user);
	}
}
