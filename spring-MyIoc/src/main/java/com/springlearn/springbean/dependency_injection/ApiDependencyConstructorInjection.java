package com.springlearn.springbean.dependency_injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 API 实现资源的依赖 Constructor 注入
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class ApiDependencyConstructorInjection {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 生成 UserHolder 的 BeanDefinition
		BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();

		// 注册 UserHolder 的 BeanDefinition
		context.registerBeanDefinition("userHolder", userHolderBeanDefinition);

		context.register(ApiDependencyConstructorInjection.class);

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		context.refresh();

		UserHolder userHolder = context.getBean(UserHolder.class);

		System.out.println(userHolder);

		context.close();

	}

	/**
	 * 为 {@link UserHolder} 生成 {@link BeanDefinition}
	 * @return
	 */
	private static BeanDefinition createUserHolderBeanDefinition() {

		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);

//		builder.addConstructorArgReference("user");
		builder.addConstructorArgReference("superUser");

		return builder.getBeanDefinition();

	}
}
