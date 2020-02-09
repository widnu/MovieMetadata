package com.imdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ibm.comparator.MovieTitleComparator;
import com.ibm.comparator.MovieYearComparator;
import com.imdb.enums.SortDirection;
import com.imdb.model.Movie;
import com.imdb.util.CSVReader;
import com.imdb.util.Constants;
import com.imdb.util.PrintUtil;
import com.imdb.util.SortingManager;

public class App {

	private static List<Movie> movieList = null;

	public static void main(String[] args) {
		readMovieFile();

//		System.out.println("before sorting: ");
//		print();
		
//		Collections.sort(movieList, new MovieYearComparator());

//		SortingManager.selectionSort(movieList, SortDirection.ASC);
		SortingManager.bubbleSort(movieList, SortDirection.ASC);
		
		PrintUtil.printMovieYear(movieList);
		
//		SortingManager.selectionSort(movieList, SortDirection.DESC);
		SortingManager.bubbleSort(movieList, SortDirection.DESC);
		
		PrintUtil.printMovieYear(movieList);
	}

	public static void readMovieFile() {
		CSVReader csvReader = new CSVReader();
		movieList = new ArrayList<Movie>();

		csvReader.read(Constants.MOVIE_METADATA, movieList);

		PrintUtil.printMovieYear(movieList);
	}

}
