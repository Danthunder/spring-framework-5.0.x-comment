package com.springlearn.springbean.dependency_injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖 Constructor 注入
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class XmlDependencyConstructorInjection {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

		String xmlResourcePath = "classpath:/META-INF/dependency-constructor-injection.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		UserHolder userHolder = beanFactory.getBean(UserHolder.class);

		System.out.println(userHolder);

	}
}
