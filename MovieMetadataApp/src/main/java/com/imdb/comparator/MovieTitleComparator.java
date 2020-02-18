package com.imdb.comparator;

import java.util.Comparator;

import com.imdb.model.Movie;

/**
 * Sorting the movies order by title ascending
 *  
 * @author widnu
 *
 */
public class MovieTitleComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		return o1.getTitle().compareTo(o2.getTitle());
	}

}
