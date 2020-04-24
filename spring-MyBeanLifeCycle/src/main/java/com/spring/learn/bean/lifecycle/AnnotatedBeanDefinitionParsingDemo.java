package com.spring.learn.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解的 BeanDefinition 解析示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-21
 **/
public class AnnotatedBeanDefinitionParsingDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 基于 Java 注解的 AnnotatedBeanDefinitionReader 实现
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

		int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
		reader.register(AnnotatedBeanDefinitionParsingDemo.class);
		int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

		System.out.println("加载了Bean的数量为 " + (beanDefinitionCountAfter - beanDefinitionCountBefore));

		AnnotatedBeanDefinitionParsingDemo bean = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);

		System.out.println(bean);
	}
}
