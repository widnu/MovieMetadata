package com.ibm.comparator;

import java.util.Comparator;

import com.imdb.model.Movie;

/**
 * Sorting the movies order by budget ascending
 *  
 * @author widnu
 *
 */
public class MovieBudgetComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		return o1.getBudget().compareTo(o2.getBudget());
	}

}
