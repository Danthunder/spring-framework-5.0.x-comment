package com.spring.learn.bean.lifecycle;

import com.springlearn.iocoverview.domain.SuperUser;
import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

import java.beans.PropertyDescriptor;

/**
 * 自定义类实现 {@link InstantiationAwareBeanPostProcessor} 接口
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-24
 **/
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
			return new SuperUser();
		}
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
			// 跳过对 User 对象的属性赋值或填入（配置元信息 -> 属性值，即 XML 中配置的 <property> 标签被忽略）
			User user = (User) bean;
			user.setId(2L);
			user.setName("王丹宁");
			return false;
		}
		return true;
	}

	// user 是跳过 Bean 属性赋值（填入）
	// superUser 也是完全跳过 Bean 的实例化（自然跳过 Bean 属性赋值）
	// userHolder
	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {

			final MutablePropertyValues propertyValues;

			if (pvs instanceof MutablePropertyValues) {
				propertyValues = (MutablePropertyValues) pvs;
			} else {
				propertyValues = new MutablePropertyValues();
			}

			// 假设配置 <property name="number" value=1 />，则 PropertyValues 对象就包含
			// 一个 PropertyValue(number=1)
			propertyValues.addPropertyValue("number", "1");

			// 原始值 <property name="description" value="the user holder" />
			if (propertyValues.contains("description")) {
				// PropertyValue 的 value 属性是 final 类型，不可变。因此只能先 remove，在添加
				propertyValues.removePropertyValue("description");
				propertyValues.addPropertyValue("description", "the user holder v2");
			}
			return propertyValues;
		}
		return pvs;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			UserHolder userHolder = (UserHolder) bean;
			userHolder.setDescription("the user holder v3");
			return userHolder;
		}
		return bean;
	}
}