package com.event.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @author: Wang danning
 * @create: 2020-02-09 22:11
 **/
public class SpringMailEvent extends ApplicationEvent {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public SpringMailEvent(Object source) {
		super(source);
	}

}
