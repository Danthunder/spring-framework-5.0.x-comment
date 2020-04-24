package com.springlearn.springbean.dependency_injection;

import com.springlearn.iocoverview.domain.User;
import com.springlearn.springbean.dependency_injection.annotation.UserGroup;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * {@link Qualifier} 注解的使用示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class LazyAnnotationDependencyInjection {

	@Autowired
	private User user;

	@Autowired
	private ObjectProvider<User> userObjectProvider; // 延迟注入

	@Autowired
	private ObjectFactory<Set<User>> userObjectFactory;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(LazyAnnotationDependencyInjection.class);

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		context.refresh();

		LazyAnnotationDependencyInjection bean = context.getBean(LazyAnnotationDependencyInjection.class);

		System.out.println("super user:" + bean.user);

		System.out.println("userObjectProvider:" + bean.userObjectProvider.getObject());

		System.out.println("userObjectFactory:" + bean.userObjectFactory.getObject());
	}
}
