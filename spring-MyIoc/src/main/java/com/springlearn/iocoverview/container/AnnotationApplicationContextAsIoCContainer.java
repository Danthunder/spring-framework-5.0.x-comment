package com.springlearn.iocoverview.container;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 注解能力的 {@link ApplicationContext} 作为 IoC 容器
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
@Configuration
public class AnnotationApplicationContextAsIoCContainer {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

//		// 将当前类作为配置类（Configuration class） --- Way one
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnotationApplicationContextAsIoCContainer.class);

		// 将当前类作为配置类（Configuration class） --- Way two
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AnnotationApplicationContextAsIoCContainer.class);
		applicationContext.refresh();


		// 依赖查找
		lookupCollectionByType(applicationContext);
	}

	/**
	 * 通过 Java 注解的方式，定义一个 Bean
	 */
	@Bean
	public User user() {
		User user = new User();
		user.setId(1L);
		user.setName("Danning Wang");
		return user;
	}





	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("依赖查找的所有 User 集合对象：" + users);
		}
	}
}
