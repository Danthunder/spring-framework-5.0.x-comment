package com.spring.learn.bean.lifecycle;

import com.springlearn.iocoverview.domain.SuperUser;
import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

import java.beans.PropertyDescriptor;

/**
 * Bean Instantiation 实例化前的操作实例
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-22
 **/
public class BeanInstantiationLifeCycleDemo {


	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 添加 BeanPostProcessor 的一个实现
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		// 添加
//		beanFactory.addBeanPostProcessor(new ApplicationContextPostProcessor);
		// 基于 XML 资源的 BeanDefinitionReader 实现
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

		String[] locations = new String[] {"META-INF/dependency-lookup-context.xml",
				"META-INF/constructor-dependency-injection.xml"};
		// 指定字符集编码 UTF-8
		int beanNumbers = reader.loadBeanDefinitions(locations);
		System.out.println("已加载的BeanDefinition数量：" + beanNumbers);
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);

		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);
	}

	static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
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
					propertyValues.addPropertyValue("description", "new description");
				}
				return propertyValues;
			}
			return pvs;
		}
	}
}
