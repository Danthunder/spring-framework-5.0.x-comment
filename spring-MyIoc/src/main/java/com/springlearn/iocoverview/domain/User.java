package com.springlearn.iocoverview.domain;

import com.springlearn.iocoverview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * @author Wang danning
 * @since 2020-04-07 22:42
 **/

public class User implements BeanNameAware {

	private Long id;

	private String name;

	private City city;

	private City[] workCities;

	private City[] liveCities;

	private Resource configFileLocation;

	private transient String beanName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Resource getConfigFileLocation() {
		return configFileLocation;
	}

	public void setConfigFileLocation(Resource configFileLocation) {
		this.configFileLocation = configFileLocation;
	}

	public static User createUser() {
		User user = new User();
		user.setName("Wang Danning");
		user.setId(1L);
		return user;
	}

	public City[] getWorkCities() {
		return workCities;
	}

	public void setWorkCities(City[] workCities) {
		this.workCities = workCities;
	}

	public City[] getLiveCities() {
		return liveCities;
	}

	public void setLiveCities(City[] liveCities) {
		this.liveCities = liveCities;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city=" + city +
				", workCities=" + Arrays.toString(workCities) +
				", liveCities=" + Arrays.toString(liveCities) +
				", configFileLocation=" + configFileLocation +
				'}';
	}

	@PostConstruct
	public void init() {
		System.out.println("用户对象初始化..." + beanName);
	}

	@PreDestroy
	public void destroy() {
		System.out.println("用户对象销毁..." + beanName);
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
}
