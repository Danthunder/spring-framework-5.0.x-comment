package com.springlearn.springbean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 {@link UserFactory} 实现
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

	// 1. 基于 @PostConstruct 注解实现
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct：User Factory 初始化");
	}

	public void initUserFactory() {
		System.out.println("@Bean(initMethod)：User Factory 初始化");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean#afterPropertiesSet(属性被设置之后执行)：User Factory 初始化");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("@PreDestroy：User Factory Bean 销毁中...");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean#destroy(属性被设置之后执行)：User Factory 销毁中...");
	}

	public void doDestroy() {
		System.out.println("自定义销毁方法：User Factory 销毁中...");
	}
}
