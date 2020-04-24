package com.springlearn.iocoverview.container;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory} 作为 IoC 容器
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
public class BeanFactoryAsIoCContainer {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		String location = "classpath:/META-INF/dependency-lookup-context.xml";
		/**
		 * 加载配置
		 * XmlBeanDefinitionReader的一个构造方法可以传递一个 BeanDefinitionRegistry 对象，而
		 * DefaultListableBeanFactory extends BeanDefinitionRegistry，故可以使用 beanFactory
		 */
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		int beanDefinitionCounter = reader.loadBeanDefinitions(location);
		System.out.println(beanDefinitionCounter);

		// 依赖查找
		lookupCollectionByType(beanFactory);
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("依赖查找的所有 User 集合对象：" + users);
		}
	}
}
