package com.imdb.comparator;

import java.util.Comparator;

import com.imdb.model.Movie;

/**
 * Sorting the movies order by title, year, IMDB score, budget, gross ascending
 * 
 * @author widnu
 *
 */
public class MovieBasicComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		int c = o1.getTitle().compareTo(o2.getTitle());
		if (c == 0)
			c = o1.getYear().compareTo(o2.getYear());
		if (c == 0)
			c = o1.getImdbScore().compareTo(o2.getImdbScore());
		if (c == 0)
			c = o1.getBudget().compareTo(o2.getBudget());
		if (c == 0)
			c = o1.getGross().compareTo(o2.getGross());
		return c;
	}

}
