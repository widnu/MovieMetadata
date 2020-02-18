package com.imdb.comparator;

import java.util.Comparator;

import com.imdb.model.Movie;

/**
 * Sorting the movies order by year ascending
 *  
 * @author widnu
 *
 */
public class MovieYearComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		return o1.getYear().compareTo(o2.getYear());
	}

}
