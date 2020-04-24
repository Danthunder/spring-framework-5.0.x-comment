package com.springlearn.springbean.bean_scope;

import com.springlearn.iocoverview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


/**
 * 自定义 {@link ThreadLocalScope} 示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-19
 **/
public class ThreadLocalScopeDemo {

	@Bean
	@Scope(ThreadLocalScope.SCOPE_NAME)
	public User user() {
		return createUser();
	}


	private static User createUser() {
		User user = new User();
		user.setId(System.nanoTime());
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ThreadLocalScopeDemo.class);
		context.addBeanFactoryPostProcessor(beanFactory -> {
			// 注册自定义 Scope
			beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
		});
		context.refresh();
		scopeBeansByLookup(context);
		context.close();
	}


	private static void scopeBeansByLookup(AnnotationConfigApplicationContext context) {
//		for (int i = 0; i < 3; i++) {
//			User user = context.getBean("user", User.class);
//			System.out.println("user = " + user);
//		}

		// 验证多线程下 ThreadLocalScope
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(()->{
				User user = context.getBean("user", User.class);
				System.out.println("Thread-ID:" +Thread.currentThread().getId() + ", user = " + user);
			});
			thread.start();

			try {
				// 强制线程执行完成，即主线程等待 thread
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
