package com.springlearn.springbean.dependency_lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-11
 **/
public class HierarchicalDependencyLookup {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HierarchicalDependencyLookup.class);

		// 获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

		// 设置 ParentBeanFactory
		HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
		beanFactory.setParentBeanFactory(parentBeanFactory);
		System.out.println("Parent BeanFactory" + beanFactory.getParentBeanFactory());

		// 测试 localBean
		displayContainsLocalBean(beanFactory, "user");
		displayContainsLocalBean(parentBeanFactory, "user");

		// 类似双亲委派的方式判断
		displayContainsBean(beanFactory, "user");
		displayContainsBean(parentBeanFactory, "user");

	}

	private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		System.out.printf("当前 BeanFactory[%s]中，是否包含 Bean[%s]: [%s]\n",
				beanFactory, beanName, containsBean(beanFactory, beanName));
	}

	private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
		if (parentBeanFactory instanceof HierarchicalBeanFactory) {
			HierarchicalBeanFactory parentHierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
			return containsBean(parentHierarchicalBeanFactory, beanName);
		}
		return beanFactory.containsLocalBean(beanName);
	}

	private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
		System.out.printf("当前 BeanFactory[%s]中，是否包含 LocalBean[%s]: [%s]\n",
				beanFactory, beanName, beanFactory.containsLocalBean(beanName));
	}


	private static ConfigurableListableBeanFactory createParentBeanFactory() {
		// 创建 BeanFactory 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		String location = "classpath:/META-INF/dependency-lookup-context.xml";
		/**
		 * 加载配置
		 * XmlBeanDefinitionReader的一个构造方法可以传递一个 BeanDefinitionRegistry 对象，而
		 * DefaultListableBeanFactory extends BeanDefinitionRegistry，故可以使用 beanFactory
		 */
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(location);

		return beanFactory;
	}
}
