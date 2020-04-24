package com.spring.learn.bean.lifecycle;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition 合并示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-21
 **/
public class MergedBeanDefinitionDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 基于 XML 资源的 BeanDefinitionReader 实现
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

		String location = "META-INF/dependency-lookup-context.xml";
		// 指定字符集编码 UTF-8
		Resource resource = new ClassPathResource(location);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int beanNumbers = reader.loadBeanDefinitions(encodedResource);
		System.out.println("已加载的BeanDefinition数量：" + beanNumbers);
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);


	}
}
