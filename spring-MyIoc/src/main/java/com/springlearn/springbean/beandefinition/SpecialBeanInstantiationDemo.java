package com.springlearn.springbean.beandefinition;

import com.springlearn.iocoverview.domain.User;
import com.springlearn.springbean.factory.DefaultUserFactory;
import com.springlearn.springbean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊 Bean 实例化方法示例
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
public class SpecialBeanInstantiationDemo {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/special-bean-Instantiation-context.xml");

		// 通过 ApplicationContext 获取 AutowireCapableBeanFactory对象
		AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
		// 通过 AutowireCapableBeanFactory 创建 UserFactory 对象
		UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
		System.out.println(userFactory.createUser());


//		// 2. 通过 ServiceLoader 方式实例化 Bean
//		ServiceLoader<UserFactory> userFactories = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
//		// Java SPI 的方式实例化 Bean
//		serviceLoaderDemo();
//		System.out.println("--------");
//		// Spring ServiceLoaderFactoryBean 的方式实例化 Bean
//		displayServerLoader(userFactories);
	}

	public static void serviceLoaderDemo() {
		ServiceLoader<UserFactory> userFactories = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
		displayServerLoader(userFactories);
	}

	public static void displayServerLoader(ServiceLoader<UserFactory> userFactories) {
		Iterator<UserFactory> iterator = userFactories.iterator();

		while (iterator.hasNext()) {
			UserFactory userFactory = iterator.next();
			System.out.println(userFactory.createUser());
		}
	}
}
