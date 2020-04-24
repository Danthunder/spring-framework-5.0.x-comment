package com.springlearn.springbean.dependency_source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源实例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-17
 **/
public class ResolvableDependencyDemo {

	@Autowired
	private String value;

	@PostConstruct
	public void init() {
		System.out.println(value);
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(ResolvableDependencyDemo.class);

		// 该 BeanFactoryPostProcessor 回调在 AbstractApplicationContext.invokeBeanFactoryPostProcessors
		// 中执行，该过程早于 Bean 的初始化
		context.addBeanFactoryPostProcessor(beanFactory -> {
			// 注册 ResolvableDependency
			beanFactory.registerResolvableDependency(String.class, "Hello world!");
		});

		context.refresh();

//		AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
//
//		if (beanFactory instanceof ConfigurableListableBeanFactory) {
//			ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
//			configurableListableBeanFactory.registerResolvableDependency(String.class, "Hello world!");
//		}

		context.close();

	}
}
