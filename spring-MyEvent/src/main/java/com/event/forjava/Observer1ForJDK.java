package com.event.forjava;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: Wang danning
 * @create: 2020-02-09 20:01
 **/
public class Observer1ForJDK implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Observer1ForJDK ----");
	}
}
