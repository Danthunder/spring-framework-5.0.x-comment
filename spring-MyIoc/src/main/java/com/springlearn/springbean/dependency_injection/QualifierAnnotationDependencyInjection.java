package com.springlearn.springbean.dependency_injection;

import com.springlearn.iocoverview.domain.User;
import com.springlearn.springbean.dependency_injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link Qualifier} 注解的使用示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-12
 **/
public class QualifierAnnotationDependencyInjection {

	@Autowired
	private User user;

	@Autowired
	@Qualifier(value="user") // 指定 Bean 的名称或者 ID
	private User namedUser;

	@Autowired
	private List<User> allUsers;

	@Autowired
	@Qualifier
	private List<User> qualifierUsers;

	@Autowired
	@UserGroup
	private List<User> groupUsers;

	@Inject
	private User injectUser;

	@Bean
	@Qualifier
	public User user1() {
		return createUser(7L);
	}

	@Bean
	@Qualifier
	public User user2() {
		return createUser(8L);
	}

	@Bean
	@UserGroup
	public User user3() {
		return createUser(9L);
	}

	@Bean
	@UserGroup
	public User user4() {
		return createUser(10L);
	}


	private User createUser(long id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(QualifierAnnotationDependencyInjection.class);

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		reader.loadBeanDefinitions(xmlResourcePath);

		context.refresh();

		QualifierAnnotationDependencyInjection bean = context.getBean(QualifierAnnotationDependencyInjection.class);

		System.out.println("super user:" + bean.user);

		System.out.println("user:" + bean.namedUser);

		System.out.println("all user:" + bean.allUsers);

		System.out.println("qualifier user:" + bean.qualifierUsers);

		System.out.println("group user:" + bean.groupUsers);

	}
}
