package com.spring.learn.bean.lifecycle;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean MetadataConfiguration 示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-20
 **/
public class BeanMetadataConfigurationDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
		String location = "META-INF/user.properties";
		// 指定字符集编码 UTF-8
		Resource resource = new ClassPathResource(location);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int beanNumbers = reader.loadBeanDefinitions(encodedResource);
		System.out.println("已加载的BeanDefinition数量：" + beanNumbers);
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);
	}
}
