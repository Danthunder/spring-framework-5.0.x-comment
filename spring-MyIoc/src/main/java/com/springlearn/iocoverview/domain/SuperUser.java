package com.springlearn.iocoverview.domain;

import com.springlearn.iocoverview.annotation.Super;

/**
 * @author Wang danning
 * @since 2020-04-07 23:20
 **/
@Super
public class SuperUser extends User {

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public String toString() {
		return "SuperUser{" +
				"address='" + address + '\'' +
				"} " + super.toString();
	}
}
