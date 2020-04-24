package com.event.springevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author: Wang danning
 * @create: 2020-02-09 22:18
 **/
@Component
public class MailBean {
	@Autowired
	private ApplicationContext applicationContext;

	public void SendMail() {
		SpringMailEvent springMailEvent = new SpringMailEvent(applicationContext);
		springMailEvent.setContent("my mail context");
		applicationContext.publishEvent(springMailEvent);
	}
}
