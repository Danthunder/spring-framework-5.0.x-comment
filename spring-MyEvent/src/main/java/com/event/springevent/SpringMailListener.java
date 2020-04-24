package com.event.springevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: Wang danning
 * @create: 2020-02-09 22:12
 **/
@Component
public class SpringMailListener implements ApplicationListener<SpringMailEvent> {
	@Override
	public void onApplicationEvent(SpringMailEvent event) {
		System.out.println("SpringMailEvent---MailContent:" + event.getContent());
	}
}
