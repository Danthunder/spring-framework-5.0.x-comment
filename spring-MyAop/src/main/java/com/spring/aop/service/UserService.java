package com.spring.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: spring.com.spring.aop.service.UserService
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-31 19:31
 * @version: 1.0
 **/
@Service
public class UserService implements com.spring.aop.service.Service {

	@Autowired
	private CityService cityService;

	public UserService() {

	}

	@Autowired
	public UserService(CityService cityService) {

	}


	public UserService(@Value("${myredis.hostname}") String a,
					   @Value("${myredis.aa}") String b) {

	}

	@Lookup
	public CityService getCityService() {
		return cityService;
	}

	public void query() {
//		System.out.println("cityService:" + cityService);
	}
}
