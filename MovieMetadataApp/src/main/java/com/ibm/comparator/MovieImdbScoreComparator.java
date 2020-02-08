package com.ibm.comparator;

import java.util.Comparator;

import com.imdb.model.Movie;

/**
 * Sorting the movies order by IMDB score ascending
 *  
 * @author widnu
 *
 */
public class MovieImdbScoreComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		return o1.getImdbScore().compareTo(o2.getImdbScore());
	}

}
