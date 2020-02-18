package com.imdb.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.imdb.comparator.MovieTitleComparator;
import com.imdb.model.Movie;

public class SearchServiceTest {

	SearchService service;

	List<Movie> movieList;

	@Before
	public void setUp() throws Exception {
		service = new SearchService();
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

		movie = new Movie("The Golden Compass", 2007, 6.1, new Double(180000000), new Double(70083519));
		movieList.add(movie);
	}

	@After
	public void tearDown() throws Exception {
		movieList = null;
	}

	@Test
	public void searchByTitleTest() throws Exception {
		Movie movie = service.searchByTitle(movieList, "The Dark Knight Rises");

		assertEquals("The Dark Knight Rises", movie.getTitle());
		assertEquals(new Integer(2012), movie.getYear());
		assertEquals(new Double(8.5), movie.getImdbScore());
		assertEquals(new Double(250000000), movie.getBudget());
		assertEquals(new Double(448130642), movie.getGross());
	}

	@Test
	public void searchByImdbScoreTest() throws Exception {
		List<Movie> resultList = service.searchByImdbScore(movieList, 6.1);
		Collections.sort(resultList, new MovieTitleComparator());

		assertEquals(2, resultList.size());
		assertEquals("Superman Returns", resultList.get(0).getTitle());
		assertEquals("The Golden Compass", resultList.get(1).getTitle());
	}

}
