package com.springlearn.springbean.dependency_source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖注入来源实例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-14
 **/
public class DependencySourceDemo {

	// 注入在 AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues 方法执行，
	// 早于 Setter 注入，也早于生命周期回调，例如 @PostConstruct
	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ApplicationContext applicationContext;


	@PostConstruct
	public void initByLookup() {
		getBean(BeanFactory.class);
		getBean(ResourceLoader.class);
		getBean(ApplicationEventPublisher.class);
		getBean(ApplicationContext.class);

	}

	private <T> T getBean(Class<T> beanType) {
		try {
			return beanFactory.getBean(beanType);
		} catch (NoSuchBeanDefinitionException ex) {
			System.err.printf("当前类型 %s 无法在 BeanFactory 中查找！\n", beanType);
		}
		return null;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(DependencySourceDemo.class);

		context.refresh();


	}
}
