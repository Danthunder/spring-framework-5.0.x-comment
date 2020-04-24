package com.event.simulation;

/**
 * @author: Wang danning
 * @create: 2020-02-09 15:33
 **/
public class TestEvent {
	public static void main(String[] args) {
		Observer1 observer1 = new Observer1();
		Observer2 observer2 = new Observer2();

		Movie movie = new Movie();

		movie.addMovieListener(observer1);
		movie.addMovieListener(observer2);

		Thread threadMovie = new Thread(movie);
		threadMovie.start();


	}

}
