package com.tx.service;

import com.tx.Repository.UserBalanceRepository;
import com.tx.entity.UserBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

/**
 * @author Wang danning
 * @since 2020-03-04 22:46
 **/
@Service
public class TransactionTemplateTypeService {
	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private UserService userService;

	@Autowired
	private UserBalanceRepository userBalanceRepository;

	public void addUserBalanceAndUserWithTT(String name, BigDecimal balance) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					System.out.println("[addUserBalanceAndUser] begin!!!");
					//1.新增用户
					userService.addUser(name);

					//2.新增用户余额
					UserBalance userBalance = new UserBalance();
					userBalance.setName(name);
					userBalance.setBalance(new BigDecimal(1000));
					userBalanceRepository.insert(userBalance);
					System.out.println("[addUserBalanceAndUser] end!!!");
				} catch (Exception ex) {
					// 注意：这里catch住异常后，设置setRollbackOnly，否则事务不会回滚
					// 建议异常不自行处理，即不try...catch，transactionTemplate.execute()
					// 会捕捉异常并回滚
					status.setRollbackOnly();
					System.out.println(ex);
				}
			}
		});
	}
}
