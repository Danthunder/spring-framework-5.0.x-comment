package com.springlearn.springbean.beandefinition;

import com.springlearn.springbean.factory.DefaultUserFactory;
import com.springlearn.springbean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化示例
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-09
 **/
@Configuration
public class BeanInitializationDemo  {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class 配置类
		context.register(BeanInitializationDemo.class);

		// 启动 Spring 容器
		context.refresh();

		System.out.println("Spring 应用上下文已经启动");
		// 非延迟初始化在 Spring 应用上下文启动完成后，被初始化


		// 依赖查找
		UserFactory userFactory = context.getBean(UserFactory.class);

		System.out.println(userFactory);

		System.out.println("Spring 应用上下文准备关闭");

		// 关闭 Spring 容器
		context.close();

		System.out.println("Spring 应用上下文已关闭");

	}

	@Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
	@Lazy
	public UserFactory userFactory(){
		return new DefaultUserFactory();
	}

}
