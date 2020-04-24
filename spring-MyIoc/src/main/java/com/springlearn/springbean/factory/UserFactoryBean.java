package com.springlearn.springbean.factory;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link org.springframework.beans.factory.FactoryBean} 实例化
 * {@link com.springlearn.iocoverview.domain.User}
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
public class UserFactoryBean implements FactoryBean {
	@Override
	public Object getObject() throws Exception {
		return User.createUser();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
