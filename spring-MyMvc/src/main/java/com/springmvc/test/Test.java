package com.springmvc.test;

import org.apache.catalina.LifecycleException;

/**
 * @author: Wang danning
 * @create: 2020-02-10 16:30
 **/
public class Test {

	public static void main(String[] args) {
		try {
			MySpringApplication.run();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}

}
