package com.springlearn.springbean.dependency_injection;

import com.springlearn.iocoverview.domain.User;
import com.springlearn.springbean.dependency_injection.annotation.InjectedUser;
import com.springlearn.springbean.dependency_injection.annotation.MyAutowired;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sun.misc.Unsafe;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 注解驱动的依赖注入的处理过程
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
@Configuration
public class AnnotationDependencyInjectionResolution {

	@Autowired
	@Lazy
	private User lazyUser;

	@Autowired         // 通过类型依赖查找
	private User user; // DependencyDescriptor->
					   // 必须（required=true）
	 				   // 实时注入（eager=true）
					   // 字段名称（"user"）
					   // 是否是首要 Primary

	@Autowired
	private Map<String, User> allUser; // 集合类型的依赖注入

//	@Autowired
	@MyAutowired
	private Optional<User> optionalUser;

	@InjectedUser
	private User injectedUser;

//	@Bean(name = "InjectUserAnnotationBeanPostProcessor")
//	public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//		AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
////		// 替换原有注解处理，使用新注解 @InjectedUser
////		beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
//
//		// 添加新注解 @InjectedUser
//		Set<Class<? extends Annotation>> autowiredAnnotationType =
//				new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
//		beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationType);
//		return beanPostProcessor;
//	}


	/**
	 * 这里相当于在 Spring 容器中存在两个 AutowiredAnnotationBeanPostProcessor，
	 * 一个是默认 AutowiredAnnotationBeanPostProcessor
	 * 一个是自定义 beanPostProcessor，专门处理自定义依赖注入注解逻辑
	 *
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE - 3)
	public AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
		AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();

		// 添加新注解 @InjectedUser
		beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
		return beanPostProcessor;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AnnotationDependencyInjectionResolution.class);

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		context.refresh();

		AnnotationDependencyInjectionResolution bean = context.getBean(AnnotationDependencyInjectionResolution.class);

		System.out.println("super user:" + bean.user);

		System.out.println("optionalUser:" + bean.optionalUser);

		System.out.println("injectedUser:" + bean.injectedUser);

	}
}
