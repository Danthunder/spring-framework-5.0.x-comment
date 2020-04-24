package com.event.simulation;

/**
 * @author: Wang danning
 * @create: 2020-02-09 15:28
 **/
public class Observer1 implements MovieListener{

	public void update(MovieEvent movieEvent) {
		System.out.println("Observer1:cry");
	}
}
