package com.springlearn.iocoverview.dependency.injection;

import com.springlearn.iocoverview.annotation.Super;
import com.springlearn.iocoverview.domain.User;
import com.springlearn.iocoverview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入
 * 1. 按照名称查找
 * 2. 按照类型来查找
 *
 * @author Wang danning
 * @since 2020-04-07 22:37
 **/
public class DependencyInjectionDemo {

	/**
	 * Spring IoC 依赖来源
	 *
	 * 自定义 Bean
	 *
	 * 容器内建 Bean 对象：例如，Environment（外部化配置和Profile的综合体）
	 *
	 * 容器内建依赖：例如，beanFactory
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// 配置 XML 配置文件，启动 Spring 应用上下文
//		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

		// 依赖来源一：自定义 Bean
		UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

		// 依赖来源二：依赖注入(内建依赖)
		System.out.println(userRepository.getBeanFactory());

		ObjectFactory objectFactory = userRepository.getObjectFactory();

		System.out.println(objectFactory.getObject());

		// 依赖查找(错误)
//		System.out.println(beanFactory.getBean(BeanFactory.class));


//		System.out.println(beanFactory);
//		System.out.println(objectFactory.getObject() == applicationContext);
		whoIsIoCContainer(userRepository, applicationContext);


		// 依赖来源三：容器内建 Bean，例如 Environment（外部化配置和Profile的综合体）
		Environment environment = (Environment) applicationContext.getBean(Environment.class);
		System.out.println("获取 Environment 类型的 Bean（spring 内建 bean）:" + environment);

	}

	private static void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext) {
		System.out.println(userRepository.getBeanFactory() == applicationContext);

		/**
		 * ApplicationContext is BeanFactory
		 * The BeanFactory interface provides an advanced configuration mechanism capable of managing any type of object.
		 *
		 */

	}


}
