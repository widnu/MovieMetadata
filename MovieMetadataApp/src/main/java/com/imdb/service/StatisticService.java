package com.imdb.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ibm.comparator.MovieBudgetComparator;
import com.ibm.comparator.MovieGrossComparator;
import com.imdb.model.Movie;

/**
 * A service for calculate statistic values of the movies
 * 
 * @author widnu
 *
 */
public class StatisticService {

	/**
	 * Calculate the mean of movies' budget
	 * 
	 * @param movieList
	 * @return the mean value
	 * @throws Exception
	 */
	public Double findMeanBudget(List<Movie> movieList) throws Exception {
		Double sumBudget = movieList.stream().mapToDouble(x -> x.getBudget().doubleValue()).sum();
		return findMean(sumBudget, movieList.size());
	}

	/**
	 * Calculate the mean of movies' gross
	 * 
	 * @param movieList
	 * @return the mean value
	 * @throws Exception
	 */
	public Double findMeanGross(List<Movie> movieList) throws Exception {
		Double sumGross = movieList.stream().mapToDouble(x -> x.getGross().doubleValue()).sum();
		return findMean(sumGross, movieList.size());
	}

	/**
	 * Calculate the median of movies' budget
	 * 
	 * @param movieList
	 * @return the median value
	 * @throws Exception
	 */
	public Double findMedianBudget(List<Movie> movieList) throws Exception {
		Collections.sort(movieList, new MovieBudgetComparator());
		List<Double> budgetList = movieList.stream().map(x -> x.getBudget()).collect(Collectors.toList());
		return findMedian(budgetList);
	}

	/**
	 * Calculate the mean of movies' gross
	 * 
	 * @param movieList
	 * @return the median value
	 * @throws Exception
	 */
	public Double findMedianGross(List<Movie> movieList) throws Exception {
		Collections.sort(movieList, new MovieGrossComparator());
		List<Double> grossList = movieList.stream().map(x -> x.getGross()).collect(Collectors.toList());
		return findMedian(grossList);
	}

	/**
	 * find the maximum budget of movies
	 * 
	 * @param movieList
	 * @return maximum budget
	 * @throws Exception
	 */
	public Double findMaxBudget(List<Movie> movieList) throws Exception {
		Collections.sort(movieList, new MovieBudgetComparator());
		return movieList.get(movieList.size() - 1).getBudget();
	}

	/**
	 * find the maximum gross of movies
	 * 
	 * @param movieList
	 * @return maximum gross
	 * @throws Exception
	 */
	public Double findMaxGross(List<Movie> movieList) throws Exception {
		Collections.sort(movieList, new MovieGrossComparator());
		return movieList.get(movieList.size() - 1).getGross();
	}

	/**
	 * find the minimum budget of movies
	 * 
	 * @param movieList
	 * @return minimum budget
	 * @throws Exception
	 */
	public Double findMinBudget(List<Movie> movieList) throws Exception {
		Collections.sort(movieList, new MovieBudgetComparator());
		return movieList.get(0).getBudget();
	}

	/**
	 * find the minimum gross of movies
	 * 
	 * @param movieList
	 * @return minimum gross
	 * @throws Exception
	 */
	public Double findMinGross(List<Movie> movieList) throws Exception {
		Collections.sort(movieList, new MovieGrossComparator());
		return movieList.get(0).getGross();
	}

	/**
	 * find mode year of movies
	 * 
	 * @param movieList
	 * @return the year that most movies were released
	 * @throws Exception
	 */
	public int findModeYear(List<Movie> movieList) throws Exception {
		int maxValue = 0, maxCount = 0;
		for (int i = 0; i < movieList.size(); ++i) {
			int count = 0;
			for (int j = 0; j < movieList.size(); ++j) {
				if (movieList.get(j).getYear().equals(movieList.get(i).getYear())) {
					++count;
				}
			}
			if (count > maxCount) {
				maxCount = count;
				maxValue = movieList.get(i).getYear();
			}
		}
		return maxValue;
	}

	/**
	 * The mean is the usual average, so I'll add and then divide: <br>
	 * (13 + 18 + 13 + 14 + 13 + 16 + 14 + 21 + 13) ÷ 9 = 15 <br>
	 * 
	 * @param sumValue
	 * @param size
	 * @return mean
	 * @throws Exception
	 */
	private Double findMean(Double sumValue, int size) throws Exception {
		return sumValue / size;
	}

	/**
	 * The median is the middle value, so first I'll have to rewrite the list in
	 * numerical order: <br>
	 * 13, 13, 13, 13, 14, 14, 16, 18, 21 <br>
	 * There are nine numbers in the list, so the middle one will be the (9 + 1) ÷ 2
	 * = 10 ÷ 2 = 5th number: <br>
	 * 13, 13, 13, 13, 14, 14, 16, 18, 21 <br>
	 * So the median is 14. <br>
	 * 
	 * @param valueList
	 * @return median
	 * @throws Exception
	 */
	private Double findMedian(List<Double> valueList) throws Exception {
		Double median = new Double(0.0);
		int numberSize = valueList.size();
		if (numberSize % 2 == 0) {
			median = (valueList.get(numberSize / 2) + valueList.get(numberSize / 2 - 1)) / 2;
		} else {
			median = valueList.get(numberSize / 2);
		}
		return median;
	}
}
