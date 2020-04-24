package com.springlearn.springbean.dependency_lookup;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 类型安全的依赖查找实例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-11
 **/
public class TypeSafetyDependencyLookupDemo {

	@Autowired
	private ObjectFactory<User> userObjectFactory;

	@Autowired
	private ObjectProvider<User> userObjectProvider;

	private static ObjectFactory<User> userObjectFactory1;

	private static ObjectProvider<User> userObjectProvider1;

	@PostConstruct
	public void init() {
		userObjectFactory1 = userObjectFactory;
		userObjectProvider1 = userObjectProvider;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(TypeSafetyDependencyLookupDemo.class);

		// 演示 BeanFactory#getBean方法的安全性
		displayBeanFactoryGetBean(context);

		// 演示 ObjectFactory#getObject方法的安全性
		displayObjectFactoryGetObject(context);

		// 演示 ObjectProvider#getIfAvailable方法的安全性
		displayObjectProviderGetIfAvailable(context);

		// 演示 ListBeanFactory#getBeansOfType方法的安全性
		displayListBeanFactoryGetBeansOfType(context);

		context.close();

	}

	private static void displayListBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
		printBeansException("displayListBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
	}

	private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext context) {
		printBeansException("displayObjectProviderGetIfAvailable", () -> userObjectProvider1.getIfAvailable());
	}

	private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext context) {
		printBeansException("displayObjectFactoryGetObject", () -> userObjectFactory1.getObject());
	}

	private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext context) {
		printBeansException("displayBeanFactoryGetBean", ()-> context.getBean(User.class));
	}

	private static void printBeansException(String source, Runnable runnable) {
		System.err.println("==================================");
		System.err.printf("source from : [%s] method... \n", source);
		try {
			runnable.run();
		} catch (BeansException ex) {
			ex.printStackTrace();
		}
	}
}
