package com.imdb.util;

import java.util.List;
import java.util.stream.Collectors;

import com.imdb.model.Movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for printing messages
 * 
 * @author widnu
 *
 */
public class PrintUtil {

	private static final Logger logger = LoggerFactory.getLogger(PrintUtil.class);

	/**
	 * Print only the titles of movies list
	 * 
	 * @param movieList
	 */
	private static void printMovieTitle(List<Movie> movieList) {
		List<String> collect = movieList.stream().map(x -> x.getTitle()).collect(Collectors.toList());
		logger.debug(collect.toString());
	}

	/**
	 * Print only the years of movies list
	 * 
	 * @param movieList
	 */
	public static void printMovieYear(List<Movie> movieList) {
		List<Integer> collect = movieList.stream().map(x -> x.getYear()).collect(Collectors.toList());
		logger.debug(collect.toString());
	}
}
