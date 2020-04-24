package com.tx.service;

import com.tx.entity.UserBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Wang danning
 * @since 2020-03-04 22:36
 **/
@Service
public class TransactionalTypeService {

	@Autowired
	private UserService userService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addUserBalanceAndUser(String name, BigDecimal balance) {
		System.out.println("[addUserBalanceAndUser] begin!!!");
		//1.新增用户
		userService.addUser(name);

		//2.新增用户余额
		UserBalance userBalance = new UserBalance();
		userBalance.setName(name);
		userBalance.setBalance(new BigDecimal(1000));
		this.addUserBalance(userBalance);
		System.out.println("[addUserBalanceAndUser] end!!!");
	}

	public void addUserBalance(UserBalance userBalance) {
		System.out.println("add user balance");
	}
}
