package com.springlearn.springbean.beandefinition;

import com.springlearn.iocoverview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
public class BeanInstantiationDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/bean-Instantiation-context.xml");

		// 通过静态方法构建
		User user = context.getBean("user-by-static-method", User.class);

		// 通过静态方法构建
		User user1 = context.getBean("user-by-instance-method", User.class);

		// 通过 FactoryBean 构建
		User user2 = context.getBean("user-by-factory-bean", User.class);


		System.out.println(user);
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user == user1);
		System.out.println(user == user2);
	}
}
