package com.spring.learn.bean.lifecycle;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * UserHolder 示例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-22
 **/
public class UserHolder implements BeanNameAware,
		BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, InitializingBean {

	private final User user;

	private Integer number;

	private String description;

	private Environment environment;

	private String name;

	private BeanFactory beanFactory;

	private ClassLoader classLoader;

	public UserHolder(User user) {
		this.user = user;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * 依赖于注解驱动
	 */
	@PostConstruct
	public void initPostConstruct() {
		// postProcessBeforeInitialization V3 -> V4
		this.description = "The user holder V4";
		System.out.println("initPostConstruct:" + description);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.description = "The user holder V5";
		System.out.println("afterPropertiesSet():" + description);
	}

	/**
	 * 自定义初始化方法
	 */
	public void init() {
		this.description = "The user holder V6";
		System.out.println("init():" + description);
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				", number=" + number +
				", description='" + description + '\'' +
				'}';
	}
}
