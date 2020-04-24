package com.springlearn.springbean.dependency_injection;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * "byName" Autowiring 依赖 Setter 方法注入示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class AutowiringByNameDependencySetterInjection {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

		String xmlResourcePath = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		UserHolder userHolder = beanFactory.getBean(UserHolder.class);

		System.out.println(userHolder);

	}
}
