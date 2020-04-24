package com.event.springevent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: Wang danning
 * @create: 2020-02-09 21:49
 **/
public class TestSpringEvent {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
//		ac.start();
		MailBean mailBean = ac.getBean(MailBean.class);
		mailBean.SendMail();
	}
}
