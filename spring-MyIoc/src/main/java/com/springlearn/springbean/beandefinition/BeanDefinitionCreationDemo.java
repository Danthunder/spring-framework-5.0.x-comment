package com.springlearn.springbean.beandefinition;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;

/**
 * {@link BeanDefinition} 构建示例
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
public class BeanDefinitionCreationDemo {

	public static void main(String[] args) {

		// 1. 通过 BeanDefinitionBuilder
		BeanDefinitionBuilder build = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		// 通过属性设置
		build.addPropertyValue("name", "Danning Wang").addPropertyValue("id",1);
		// 获取 BeanDefinition 实例
		AbstractBeanDefinition beanDefinition = build.getBeanDefinition();
		// BeanDefinition 并非 Bean 终态，可以自定义修改
		beanDefinition.setScope("");
//		BeanDefinitionBuilder.rootBeanDefinition();

		// 2. 通过 AbstractBeanDefinition 以及派生类
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		// 设置 Bean 类型
		genericBeanDefinition.setBeanClass(User.class );
		// 通过 MutablePropertyValues 批量操作属性
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		// 方式一
//		propertyValues.addPropertyValue("name", "Danning Wang");
//		propertyValues.addPropertyValue("id",1);
		// 方式二，build链式调用
		propertyValues.add("id",1).add("name", "Danning Wang");
		// 通过 setPropertyValues() 批量操作属性
		genericBeanDefinition.setPropertyValues(propertyValues);

	}
}
