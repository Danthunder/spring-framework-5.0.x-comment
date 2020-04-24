package com.springlearn.springbean.dependency_lookup;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-10
 **/

@Configuration
public class ObjectProviderDemo {

	@Autowired
	private ObjectProvider<User> beanProvider;

	private static ObjectProvider<User> userBeanProvider;

	@PostConstruct
	public void init() {
		userBeanProvider = beanProvider;
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ObjectProviderDemo.class);

//		User user = userBeanProvider.getIfAvailable(() -> User.createUser());
		User user = userBeanProvider.getIfAvailable(User::createUser);
		System.out.println(user);

//		lookupByObjectProvider(context);

	}

	private static void lookupByObjectProvider(AnnotationConfigApplicationContext context) {

	}

	@Bean
	public String helloWorld() { // 如果没有定义 BeanName，默认方法名即为 BeanName
		return "Hello world!";
	}



}
