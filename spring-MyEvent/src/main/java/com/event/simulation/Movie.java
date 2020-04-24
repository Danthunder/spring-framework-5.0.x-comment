package com.event.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Wang danning
 * @create: 2020-02-09 15:23
 **/
public class Movie implements Runnable{

	private List<MovieListener> movieListenerList = new ArrayList<>();

	public void addMovieListener(MovieListener movieListener) {
		movieListenerList.add(movieListener);
	}

	public void play() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		MovieEvent movieEvent = new MovieEvent("lonely", 1);

		for (MovieListener movieListener : movieListenerList) {
			movieListener.update(movieEvent);
		}
	}

	@Override
	public void run() {
		System.out.println("movie start play");
		play();
	}
}
