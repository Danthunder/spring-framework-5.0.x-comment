package com.springlearn.springbean.dependency_injection;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Java 注解的资源的依赖 constructor 注入
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class AnnotationDependencyConstructorInjection {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(AnnotationDependencyConstructorInjection.class);

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		context.refresh();

		UserHolder userHolder = context.getBean(UserHolder.class);

		System.out.println(userHolder);

		context.close();

	}

	@Bean
	public UserHolder userHolder(User user) {
		return new UserHolder(user);
	}
}
