package com.tx.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Wang danning
 * @since 2020-03-04 22:40
 **/
@Component
public class UserBalance {

	private String name;

	private BigDecimal balance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
