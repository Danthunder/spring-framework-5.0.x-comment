package com.tx.service;

import org.springframework.stereotype.Service;

/**
 * @author Wang danning
 * @since 2020-03-04 22:42
 **/
@Service
public class UserService {

	public void addUser(String name) {
		System.out.println("add user" + name);
	}
}
