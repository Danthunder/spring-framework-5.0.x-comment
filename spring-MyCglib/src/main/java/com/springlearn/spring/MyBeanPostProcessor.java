package com.springlearn.spring;


import com.springlearn.service.City;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @program: spring.com.springlearn.spring.MyBeanPostProcessor
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-06 23:37
 * @version: 1.0
 **/
/*
1. 实现了BeanPostProcessor接口的自定义后置处理器BeanPostProcess，插手spring bean的实例化的前后
2. 实例化bean时的执行顺序：先执行其bean的构造方法，然后在初始化方法之前执行一遍（postProcessBeforeInitialization）
	，执行初始化方法（用@PostConstruct注解的这种lifecycle callbacks），然后在初始化方法之后再执行一遍
	（postProcessAfterInitialization）。
3. spring 实例化bean的周期有许多步骤（只有在最后阶段，即完成整个生命周期，才能成为spring bean，之前的阶段或
	步骤中只能叫做object）。
	1) 当new出该对象时，首先执行其构造方法（此时只是一个对象，还不是spring bean）后，将该对象往后续方法/步骤
		中传递
	2) 然后在该方法/步骤中，通过传递上一步的obj对象，循环（遍历）执行后置处理器集合（都是后置处理器BeanPostProcess
		的实现类对象，spring内部有很多，也可以程序员提供）中的各个后置处理器的postProcessBeforeInitialization
		方法（有些是空实现，返回null，spring就用该原始对象进行后续传递；有些处理了Object bean对象，返回proxy object
		代理对象---aop的实现原理，那spring就用返回的代理对象进行后续传递，最终产生的spring bean就是代理对象！）。注意
		这里将的是BeanPostProcessor这种后置处理器，实际上spring内部有大量的后置处理器。在执行createBean()方法时，会
		执行9次后置处理器，并不是只执行这个BeanPostProcessor子类的后置处理器。


 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("cityService")) {
			System.out.println("before");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("cityService")) {
			System.out.println("after");
			Class[] classes = new Class[]{City.class};
			return Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(), classes, new MyInvocationHandler(bean));
		}
		return bean;
	}
}
