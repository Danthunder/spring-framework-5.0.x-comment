package com.springlearn.springbean.dependency_lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例代码
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-11
 **/
public class NoUniqueBeanDefinitionExceptionDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(NoUniqueBeanDefinitionExceptionDemo.class);
		try {
			String str = context.getBean(String.class);
		} catch (NoUniqueBeanDefinitionException e) {
			System.out.printf("Spring 上下文中，共存在[%d]个类型为[%s]的 Bean，具体信息为%n%s",
					e.getNumberOfBeansFound(),
					e.getBeanType(),
					e.getMessage());
		}

	}

	@Bean
	public String bean1() {
		return "bean1";
	}

	@Bean
	public String bean2() {
		return "bean2";
	}

	@Bean
	public String bean3() {
		return "bean3";
	}

}
