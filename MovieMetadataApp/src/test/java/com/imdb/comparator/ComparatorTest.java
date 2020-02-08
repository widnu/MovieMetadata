package com.imdb.comparator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.comparator.MovieBudgetComparator;
import com.ibm.comparator.MovieGrossComparator;
import com.ibm.comparator.MovieImdbScoreComparator;
import com.ibm.comparator.MovieTitleComparator;
import com.ibm.comparator.MovieYearComparator;
import com.imdb.model.Movie;

public class ComparatorTest {

	List<Movie> movieList;

	List<String> titleAscList = Arrays.asList("Avatar", "Iron Man 3", "Jurassic World", "Superman Returns",
			"The Dark Knight Rises");

	List<String> titleDescList;

	List<Integer> yearAscList = Arrays.asList(2006, 2009, 2012, 2013, 2015);

	List<Integer> yearDescList;

	List<Double> imdbScoreAscList = Arrays.asList(6.1, 7.0, 7.2, 7.9, 8.5);

	List<Double> imdbScoreDescList;

	List<Double> budgetAscList = Arrays.asList(150000000.0, 200000000.0, 209000000.0, 237000000.0, 250000000.0);

	List<Double> budgetDescList;

	List<Double> grossAscList = Arrays.asList(200069408.0, 408992272.0, 448130642.0, 652177271.0, 760505847.0);

	List<Double> grossDescList;

	@Before
	public void setUp() throws Exception {
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

		// reverse the expected results of asc order to create desc lists
		titleDescList = titleAscList.stream().collect(Collectors.toList());
		Collections.reverse(titleDescList);

		yearDescList = yearAscList.stream().collect(Collectors.toList());
		Collections.reverse(yearDescList);

		imdbScoreDescList = imdbScoreAscList.stream().collect(Collectors.toList());
		Collections.reverse(imdbScoreDescList);

		budgetDescList = budgetAscList.stream().collect(Collectors.toList());
		Collections.reverse(budgetDescList);

		grossDescList = grossAscList.stream().collect(Collectors.toList());
		Collections.reverse(grossDescList);
	}

	@After
	public void tearDown() throws Exception {
		movieList = null;
	}

	@Test
	public void testSortTitleAsc() {
		Collections.sort(movieList, new MovieTitleComparator());
		List<String> result = movieList.stream().map(x -> x.getTitle()).collect(Collectors.toList());
		assertEquals(titleAscList, result);
	}

	@Test
	public void testSortTitleDesc() {
		Comparator<Movie> c = Collections.reverseOrder(new MovieTitleComparator());
		Collections.sort(movieList, c);

		List<String> result = movieList.stream().map(x -> x.getTitle()).collect(Collectors.toList());
		assertEquals(titleDescList, result);
	}

	@Test
	public void testSortYearAsc() {
		Collections.sort(movieList, new MovieYearComparator());
		List<Integer> result = movieList.stream().map(x -> x.getYear()).collect(Collectors.toList());
		assertEquals(yearAscList, result);
	}

	@Test
	public void testSortYearDesc() {
		Comparator<Movie> c = Collections.reverseOrder(new MovieYearComparator());
		Collections.sort(movieList, c);

		List<Integer> result = movieList.stream().map(x -> x.getYear()).collect(Collectors.toList());
		assertEquals(yearDescList, result);
	}

	@Test
	public void testSortImdbScoreAsc() {
		Collections.sort(movieList, new MovieImdbScoreComparator());
		List<Double> result = movieList.stream().map(x -> x.getImdbScore()).collect(Collectors.toList());
		assertEquals(imdbScoreAscList, result);
	}

	@Test
	public void testSortImdbScoreDesc() {
		Comparator<Movie> c = Collections.reverseOrder(new MovieImdbScoreComparator());
		Collections.sort(movieList, c);

		List<Double> result = movieList.stream().map(x -> x.getImdbScore()).collect(Collectors.toList());
		assertEquals(imdbScoreDescList, result);
	}

	@Test
	public void testSortBudgetAsc() {
		Collections.sort(movieList, new MovieBudgetComparator());
		List<Double> result = movieList.stream().map(x -> x.getBudget()).collect(Collectors.toList());
		assertEquals(budgetAscList, result);
	}

	@Test
	public void testSortBudgetDesc() {
		Comparator<Movie> c = Collections.reverseOrder(new MovieBudgetComparator());
		Collections.sort(movieList, c);

		List<Double> result = movieList.stream().map(x -> x.getBudget()).collect(Collectors.toList());
		assertEquals(budgetDescList, result);
	}

	@Test
	public void testSortGrossAsc() {
		Collections.sort(movieList, new MovieGrossComparator());
		List<Double> result = movieList.stream().map(x -> x.getGross()).collect(Collectors.toList());
		assertEquals(grossAscList, result);
	}

	@Test
	public void testSortGrossDesc() {
		Comparator<Movie> c = Collections.reverseOrder(new MovieGrossComparator());
		Collections.sort(movieList, c);

		List<Double> result = movieList.stream().map(x -> x.getGross()).collect(Collectors.toList());
		assertEquals(grossDescList, result);
	}
}
