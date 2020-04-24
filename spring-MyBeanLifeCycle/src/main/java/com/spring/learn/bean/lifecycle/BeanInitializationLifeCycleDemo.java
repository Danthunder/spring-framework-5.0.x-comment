package com.spring.learn.bean.lifecycle;

import com.springlearn.iocoverview.domain.SuperUser;
import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.util.ObjectUtils;

import java.beans.PropertyDescriptor;

/**
 * Bean 初始化的操作实例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-22
 **/
public class BeanInitializationLifeCycleDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 注意：Bean 的注册只能在 ApplicationContext 中，因此这里只能通过添加 BeanPostProcessor 完成后置处理器的注册
		// 但是最终底层实现是一样的，即通过 beanFactory.addBeanPostProcessor 方式添加 BeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		// 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct 处理问题
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

		// 基于 XML 资源的 BeanDefinitionReader 实现
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

		String[] locations = new String[] {"META-INF/dependency-lookup-context.xml",
				"META-INF/constructor-dependency-injection.xml"};
		// 指定字符集编码 UTF-8
		int beanNumbers = reader.loadBeanDefinitions(locations);
		System.out.println("已加载的BeanDefinition数量：" + beanNumbers);
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);

		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);
	}


}
