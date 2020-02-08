package com.ibm.comparator;

import java.util.Comparator;

import com.imdb.model.Movie;

/**
 * Sorting the movies order by gross ascending
 *  
 * @author widnu
 *
 */
public class MovieGrossComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		return o1.getGross().compareTo(o2.getGross());
	}

}
