package com.springlearn.springbean.factory;

import com.springlearn.iocoverview.domain.User;

/**
 * {@link User} 工厂类
 *
 * @author <a herf="mailto:dandanwdn@163.com"/>
 * @since 2020-04-08
 **/
public interface UserFactory {

	default User createUser() {
		return User.createUser();
	}
}
