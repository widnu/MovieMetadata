package com.imdb.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.imdb.model.Movie;

public class StatisticServiceTest {

	StatisticService service;

	List<Movie> movieList;

	@Before
	public void setUp() throws Exception {
		service = new StatisticService();
		movieList = new ArrayList<Movie>();

		Movie movie = new Movie("Avatar", 2009, 7.9, new Double(237000000), new Double(760505847));
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
	public void findMeanBudgetTest() throws Exception {
		Double mean = service.findMeanBudget(movieList);
		Double expected = new Double((237000000.0 + 250000000.0 + 209000000.0 + 150000000.0 + 200000000.0) / 5);
		assertEquals(expected, mean);
	}

	@Test
	public void findMeanGrossTest() throws Exception {
		Double mean = service.findMeanGross(movieList);
		Double expected = new Double((760505847.0 + 448130642.0 + 200069408.0 + 652177271.0 + 408992272.0) / 5);
		assertEquals(expected, mean);
	}

	@Test
	public void findMedianBudgetTest() throws Exception {
		Double median = service.findMedianBudget(movieList);
		assertEquals(new Double(209000000.0), median);
	}

	@Test
	public void findMedianGrossTest() throws Exception {
		Double median = service.findMedianGross(movieList);
		assertEquals(new Double(448130642.0), median);
	}

	@Test
	public void findMaxBudgetTest() throws Exception {
		Double max = service.findMaxBudget(movieList);
		assertEquals(new Double(250000000), max);
	}

	@Test
	public void findMaxGrossTest() throws Exception {
		Double max = service.findMaxGross(movieList);
		assertEquals(new Double(760505847), max);
	}

	@Test
	public void findMinBudgetTest() throws Exception {
		Double min = service.findMinBudget(movieList);
		assertEquals(new Double(150000000), min);
	}

	@Test
	public void findMinGrossTest() throws Exception {
		Double min = service.findMinGross(movieList);
		assertEquals(new Double(200069408), min);
	}

	@Test
	public void findModeYearTest() throws Exception {
		Movie movie = new Movie("Superman Returns 2", 2006, 6.1, new Double(209000000), new Double(200069408));
		movieList.add(movie);

		movie = new Movie("Jurassic World 2", 2015, 7.0, new Double(150000000), new Double(652177271));
		movieList.add(movie);

		movie = new Movie("Superman Returns 3", 2006, 6.1, new Double(209000000), new Double(200069408));
		movieList.add(movie);

		int mode = service.findModeYear(movieList);
		assertEquals(2006, mode);

	}
}
