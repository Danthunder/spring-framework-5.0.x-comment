package com.springlearn.springbean.bean_scope;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Spring Bean 作用域实例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-18
 **/
public class BeanScopeDemo implements DisposableBean {

	@Bean
	// 默认 scope 是 “singleton”
	public static User singletonUser() {
		return createUser();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public static User prototypeUser() {
		return createUser();
	}

	private static User createUser() {
		User user = new User();
		user.setId(System.nanoTime());
		return user;
	}

	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser;

	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser2;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser2;

	@Autowired
	private Map<String, User> users;

	@Autowired
	private ConfigurableListableBeanFactory beanFactory; // resolvable dependency

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BeanScopeDemo.class);

		context.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
				@Override
				public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
					System.out.printf("%s Bean 名称 %s 在初始化后回调...",
							bean.getClass().getName(), beanName);
					return bean;
				}
			});
		});
		context.refresh();

		// 结论一：
		// Singleton Bean 无论依赖查找还是依赖注入，均为同一对象
		// Prototype Bean 无论依赖查找还是依赖注入，均生成新对象

		// 结论二：
		// 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
		// Prototype Bean 有别于其他地方的依赖注入的 Prototype Bean

		// 结论三：
		// 无论是 Singleton 还是 Prototype Bean 均会执行初始化回调
		// 不过仅 Singleton Bean 会执行销毁回调

//		scopeBeansByLookup(context);
		scopeBeansByInjection(context);
		context.close();
	}

	private static void scopeBeansByInjection(AnnotationConfigApplicationContext context) {
		BeanScopeDemo bean = context.getBean(BeanScopeDemo.class);
		System.out.println("singletonUser = " + bean.singletonUser);
		System.out.println("singletonUser2 = " + bean.singletonUser2);
		System.out.println("prototypeUser = " + bean.prototypeUser);
		System.out.println("prototypeUser = " + bean.prototypeUser2);

		System.out.println("users = " + bean.users);
	}

	private static void scopeBeansByLookup(AnnotationConfigApplicationContext context) {

		for (int i = 0; i < 3; i++) {

			User singletonBeanUser = context.getBean("singletonUser", User.class);
			System.out.println("singletonBeanUser = " + singletonBeanUser);

			User prototypeBeanUser = context.getBean("prototypeUser", User.class);
			System.out.println("prototypeBeanUser = " + prototypeBeanUser);
			
		}

	}

	@Override
	public void destroy() throws Exception {
		this.prototypeUser.destroy();
		this.prototypeUser2.destroy();
		for (Map.Entry<String, User> entry : this.users.entrySet()) {
			String beanName = entry.getKey();
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
			if (beanDefinition.isPrototype()) {
				entry.getValue().destroy();
			}
		}
	}
}
