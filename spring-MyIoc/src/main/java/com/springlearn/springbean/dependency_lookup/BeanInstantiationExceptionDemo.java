package com.springlearn.springbean.dependency_lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 示例代码
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-11
 **/
public class BeanInstantiationExceptionDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();

		context.register(BeanInstantiationExceptionDemo.class);

		BeanDefinitionBuilder build = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
		context.registerBeanDefinition("errorBean", build.getBeanDefinition());

		context.refresh();

		context.close();

	}
}
