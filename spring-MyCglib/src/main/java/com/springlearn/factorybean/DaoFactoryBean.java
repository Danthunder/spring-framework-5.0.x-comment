package com.springlearn.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @program: spring.com.springlearn.dao.DaoFactoryBean
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-23 16:24
 * @version: 1.0
 **/

/**
 * 如果你的类实现了FactoryBean
 * 那么Spring容器当中存在两个对象
 * 一个是叫getObject()返回的对象
 * 一个是当前对象
 *
 * 在Spring中如何保存的beanName
 * getObject()得到对象存的是当前类指定的名字
 * 当前对象是"&"+当前类名字
 */
@Component
public class DaoFactoryBean implements FactoryBean {

	public void testBean() {
		System.out.println("DaoFactoryBean testBean");
	}

	@Override
	public Object getObject() throws Exception {
		return new TempDaoFactoryBean();
	}

	@Override
	public Class<?> getObjectType() {
		return TempDaoFactoryBean.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
