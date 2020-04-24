package com.event.simulation;

/**
 * @author: Wang danning
 * @create: 2020-02-09 15:57
 **/
public class MovieEvent {

	private String context;

	private int type;

	public Object source;

	public MovieEvent(Object source) {
		this.source = source;
	}

	public MovieEvent(String context, int type){
		this.context = context;
		this.type = type;
	}


	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
