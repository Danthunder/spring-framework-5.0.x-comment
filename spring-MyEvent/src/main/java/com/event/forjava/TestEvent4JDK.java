package com.event.forjava;

/**
 * @author: Wang danning
 * @create: 2020-02-09 20:12
 **/
public class TestEvent4JDK {
	public static void main(String[] args) {
		Observer1ForJDK observer1ForJDK = new Observer1ForJDK();
		Observer2ForJDK observer2ForJDK = new Observer2ForJDK();

		Movie4JDK movie4JDK = new Movie4JDK();
		movie4JDK.addObserver(observer1ForJDK);
		movie4JDK.addObserver(observer2ForJDK);


	}
}
