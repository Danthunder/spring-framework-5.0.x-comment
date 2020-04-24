package com.springlearn.springbean.dependency_injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 {@link org.springframework.beans.factory.Aware} 回调的依赖注入示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class AwareInterfaceDependencyInjection implements BeanFactoryAware, ApplicationContextAware {

	private static BeanFactory beanFactory;

	private static ApplicationContext applicationContext;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(AwareInterfaceDependencyInjection.class);

		context.refresh();

		System.out.println(beanFactory == context.getBeanFactory());

		System.out.println(applicationContext == context);

	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		AwareInterfaceDependencyInjection.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AwareInterfaceDependencyInjection.applicationContext = applicationContext;
	}
}
