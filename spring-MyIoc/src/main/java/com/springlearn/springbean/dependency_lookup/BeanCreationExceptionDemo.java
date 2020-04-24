package com.springlearn.springbean.dependency_lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 *{@link BeanCreationException} 示例代码
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class BeanCreationExceptionDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();

		context.register(BeanCreationExceptionDemo.class);

		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
		context.registerBeanDefinition("POJO", builder.getBeanDefinition());

		context.refresh();

		context.close();
	}

	// create bean fast-fail
	static class POJO implements InitializingBean {

		@PostConstruct
		public void init() throws Exception {
			throw new Exception("init: for propose...");
		}

		@Override
		public void afterPropertiesSet() throws Exception {
			throw new Exception("afterPropertiesSet: for propose...");
		}
	}

}
