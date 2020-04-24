package com.springlearn.iocoverview.repository;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户信息仓库
 *
 * @author Wang danning
 * @since 2020-04-08 08:53
 **/
public class UserRepository {

	private Collection<User> users; // 自定义 Bean

	private BeanFactory beanFactory; // 内建的、非 Bean 对象（依赖）

	private ObjectFactory<ApplicationContext> objectFactory;

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public ObjectFactory<ApplicationContext> getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
		this.objectFactory = objectFactory;
	}

	@Override
	public String toString() {
		return "UserRepository{" +
				"users=" + users +
				'}';
	}
}
