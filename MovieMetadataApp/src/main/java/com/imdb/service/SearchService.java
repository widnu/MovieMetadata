package com.imdb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.imdb.comparator.MovieTitleComparator;
import com.imdb.model.Movie;

/**
 * A service for searching movies
 * 
 * @author widnu
 *
 */
public class SearchService {

	/**
	 * Perform binary search to find the movie by title.
	 * 
	 * @param movieList
	 * @param movieTitle
	 * @return the movie with matched title
	 * @throws Exception
	 */
	public Movie searchByTitle(List<Movie> movieList, String title) throws Exception {
		Collections.sort(movieList, new MovieTitleComparator());
		int movieIndex = binarySearchMovieIndex(movieList, title, 0, movieList.size());
		return movieList.get(movieIndex);
	}

	/**
	 * Perform linear search to find the movie by imdb score
	 * 
	 * @param movieList
	 * @param imdbScore
	 * @return list of movies found by the exact imdb score
	 * @throws Exception
	 */
	public List<Movie> searchByImdbScore(List<Movie> movieList, Double imdbScore) throws Exception {
		List<Movie> searchList = new ArrayList<Movie>();
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getImdbScore().equals(imdbScore)) {
				searchList.add(movieList.get(i));
			}
		}
		return searchList;
	}

	/**
	 * Loop through the list to find the movie with matched title by binary search
	 * algorithm
	 * 
	 * @param movieList
	 * @param title
	 * @param beginIdx
	 * @param endIdx
	 * @return
	 *         <ul>
	 *         <li>index of the found item</li>
	 *         <li>-1 when the item is not found</li>
	 *         <ul>
	 * @throws Exception
	 */
	private int binarySearchMovieIndex(List<Movie> movieList, String title, int beginIdx, int endIdx) throws Exception {
		int index = -1;
		while (beginIdx <= endIdx) {
			int midIdx = (beginIdx + endIdx) / 2;
			int comparedResult = movieList.get(midIdx).getTitle().compareTo(title);

			if (comparedResult < 0) {
				beginIdx = midIdx + 1;
			} else if (comparedResult > 0) {
				endIdx = midIdx - 1;
			} else if (comparedResult == 0) {
				index = midIdx;
				break;
			}
		}
		return index;
	}

}
