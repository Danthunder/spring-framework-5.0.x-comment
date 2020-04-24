package com.springlearn.springbean.dependency_injection;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Java 注解的，通过字段注入的代码示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class AnnotationDependencyFieldInjection {

	@Autowired // @Autowired 会忽略掉静态字段
	private UserHolder userHolder;

	@Resource
	private UserHolder userHolder2;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 配置类 Configuration Class -> Spring Bean
		context.register(AnnotationDependencyFieldInjection.class);

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		context.refresh();

		AnnotationDependencyFieldInjection bean = context.getBean(AnnotationDependencyFieldInjection.class);

		// @Autowired 字段关联
		UserHolder userHolder = bean.userHolder;

		System.out.println(userHolder);
		System.out.println(bean.userHolder2);

		context.close();

	}

	@Bean
	public UserHolder userHolder(User user) {
		UserHolder holder = new UserHolder();
		holder.setUser(user);
		return holder;
	}
}
