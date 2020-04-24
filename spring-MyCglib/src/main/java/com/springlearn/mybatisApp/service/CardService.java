package com.springlearn.mybatisApp.service;

import com.springlearn.mybatisApp.dao.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: spring.com.springlearn.mybatisApp.service.CardService
 * @description: TODO
 * @author: Wang danning
 * @create: 2020-01-25 23:05
 * @version: 1.0
 **/
@Service
public class CardService {

	@Autowired
	private CardDao dao;

	public void list() {
		System.out.println(dao.list("is"));
		System.out.println(dao.list("is"));
		System.out.println(dao.list("is"));
	}

}
