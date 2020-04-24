package com.event.forjava;

import java.util.Observable;

/**
 * @author: Wang danning
 * @create: 2020-02-09 20:07
 **/
public class Movie4JDK extends Observable {

	public void event() {
		setChanged();
		notifyObservers();
	}
}
