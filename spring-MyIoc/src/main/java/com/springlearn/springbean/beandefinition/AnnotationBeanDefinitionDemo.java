package com.springlearn.springbean.beandefinition;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解 BeanDefinition 示例
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class（配置类）
		context.register(AnnotationBeanDefinitionDemo.class);

		registerBeanDefinition(context, "meWangdanning", User.class);
		registerBeanDefinition(context, User.class);

		context.refresh();



		System.out.println("Config 类型的所有 Bean：" + context.getBeansOfType(Config.class));
		System.out.println("User 类型的所有 Bean：" + context.getBeansOfType(User.class));
		// 1. 通过 @Bean 的方式定义
		// 2. 通过 @Component
		// 3. 通过 @Import来进行导入
	}

	/**
	 * 命名 Bean 的注册方式
	 * @param registry
	 * @param beanName
	 * @param beanClass
	 */
	public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
		BeanDefinitionBuilder builder = genericBeanDefinition(beanClass);
		builder.addPropertyValue("id",1L).addPropertyValue("name","Wang Danning");

		if (StringUtils.hasText(beanName)) {
			// 命名方式注册 BeanDefinition
			registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
		} else {
			// 非命名方式注册 BeanDefinition
			BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
		}

	}

	public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
		registerBeanDefinition(registry, null, beanClass);
	}

	@Component
	public static class Config {

		@Bean(name={"user","meUser"})
		public User user() {
			User user = new User();
			user.setName("Wang Danning");
			user.setId(1L);
			return user;
		}

	}


}
