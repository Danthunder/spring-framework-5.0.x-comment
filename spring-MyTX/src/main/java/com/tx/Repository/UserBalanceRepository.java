package com.tx.Repository;

import com.tx.entity.UserBalance;
import org.springframework.stereotype.Repository;

/**
 * @author Wang danning
 * @since 2020-03-04 23:00
 **/
@Repository
public class UserBalanceRepository {
	public void insert(UserBalance userBalance) {
		System.out.println("insert");
	}
}
