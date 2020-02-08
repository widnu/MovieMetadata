package com.imdb.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.imdb.enums.SortDirection;
import com.imdb.model.Movie;

public class SortingManagerTest {

	List<Movie> movieList;

	String yearAsc = "[2006, 2009, 2012, 2013, 2015]";
	String yearDesc = "[2015, 2013, 2012, 2009, 2006]";

	@Before
	public void setUp() throws Exception {
		movieList = new ArrayList<Movie>();

		Movie movie = new Movie("Avatar", 2009, 7.9, new Double(237000000), new Double(760505847d));
		movieList.add(movie);

		movie = new Movie("The Dark Knight Rises", 2012, 8.5, new Double(250000000), new Double(448130642));
		movieList.add(movie);

		movie = new Movie("Superman Returns", 2006, 6.1, new Double(209000000), new Double(200069408));
		movieList.add(movie);

		movie = new Movie("Jurassic World", 2015, 7.0, new Double(150000000), new Double(652177271));
		movieList.add(movie);

		movie = new Movie("Iron Man 3", 2013, 7.2, new Double(200000000), new Double(408992272));
		movieList.add(movie);
	}

	@After
	public void tearDown() throws Exception {
		movieList = null;
	}

	@Test
	public void testSelectionSortYearAsc() {
		SortingManager.selectionSort(movieList, SortDirection.ASC);
		String result = movieList.stream().map(x -> x.getYear()).collect(Collectors.toList()).toString();
		assertEquals(yearAsc, result);
	}

	@Test
	public void testSelectionSortYearDesc() {
		SortingManager.selectionSort(movieList, SortDirection.DESC);
		String result = movieList.stream().map(x -> x.getYear()).collect(Collectors.toList()).toString();
		assertEquals(yearDesc, result);
	}

	@Test
	public void testBubbleSortMovieYearAsc() {
		SortingManager.bubbleSort(movieList, SortDirection.ASC);
		String result = movieList.stream().map(x -> x.getYear()).collect(Collectors.toList()).toString();
		assertEquals(yearAsc, result);
	}

	@Test
	public void testBubbleSortMovieYearDesc() {
		SortingManager.bubbleSort(movieList, SortDirection.DESC);
		String result = movieList.stream().map(x -> x.getYear()).collect(Collectors.toList()).toString();
		assertEquals(yearDesc, result);
	}
}
