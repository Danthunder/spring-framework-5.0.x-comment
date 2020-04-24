package com.springlearn.springbean.dependency_source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * Spring 外部化配置示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-18
 **/
@Configuration
@PropertySource(value = "/META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

	@Value("${user.id:-1}")
	private Long id;

	@Value("${usr.name:哈哈}")
	private String name;

	@Value("{user.resource:classpath://default.properties}")
	private Resource resource;


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ExternalConfigurationDependencySourceDemo.class);
		context.refresh();

		ExternalConfigurationDependencySourceDemo bean = context.getBean(ExternalConfigurationDependencySourceDemo.class);

		System.out.println("user id = " + bean.id);
		System.out.println("usr name = " + bean.name);
		System.out.println("user resource = " + bean.resource);
		context.close();
	}

}
