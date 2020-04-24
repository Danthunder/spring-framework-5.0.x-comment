package com.springlearn.springbean.beandefinition;

import com.springlearn.springbean.factory.DefaultUserFactory;
import com.springlearn.springbean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体 Bean 注册
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-09
 **/
public class SingletonBeanRegistrationDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 创建一个外部 UserFactory 对象
		UserFactory userFactory = new DefaultUserFactory();

		// 获取 BeanFactory （AnnotationConfigApplicationContext 通过委派 getBeanFactory 获取 ）
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

		// 注册外部单例对象
		beanFactory.registerSingleton("userFactory", userFactory);

		// 通过依赖查找的方式
		System.out.println(beanFactory.getBean("userFactory", UserFactory.class));

		System.out.println(userFactory == beanFactory.getBean("userFactory", UserFactory.class));


	}

}
