package com.springlearn.springbean.dependency_injection;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Java 注解的，通过方法注入的代码示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
			* @since 2020-04-12
			**/
public class AnnotationDependencyMethodInjection {

	private UserHolder userHolder;

	private UserHolder userHolder2;

	@Autowired
	public void init1(UserHolder userHolder) {
		this.userHolder = userHolder;
	}

	@Resource
	public void init2(UserHolder userHolder2) {
		this.userHolder2 = userHolder2;
	}

	@Bean
	public UserHolder userHolder(User user) {
		UserHolder holder = new UserHolder();
		holder.setUser(user);
		return holder;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 配置类 Configuration Class -> Spring Bean
		context.register(AnnotationDependencyMethodInjection.class);

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		context.refresh();

		AnnotationDependencyMethodInjection bean = context.getBean(AnnotationDependencyMethodInjection.class);

		// @Autowired 字段关联
		UserHolder userHolder = bean.userHolder;

		System.out.println(userHolder);
		System.out.println(bean.userHolder2);

		System.out.println(userHolder == bean.userHolder2);

		context.close();

	}

}
