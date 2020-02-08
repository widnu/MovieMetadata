package com.imdb.util;

import java.util.List;

import com.imdb.enums.SortDirection;
import com.imdb.model.Movie;

/**
 * A class for sorting methods with specific algorithms such as selection and bubble sorting
 * 
 * @author widnu
 *
 */
public class SortingManager {

	/**
	 * Performing Selection sort by finding the smallest item and move it to the
	 * head of the list
	 * 
	 * @param movieList
	 * @param derection
	 */
	public static void selectionSort(List<Movie> movieList, SortDirection derection) {
		// the 1st loop will go through all items in the list from head to tail
		for (int i = 0; i < movieList.size() - 1; i++) {
			int index = i;

			// the second loop will find the smaller item to swap
			for (int j = i + 1; j < movieList.size(); j++) {
				if (SortDirection.ASC.equals(derection)) {
					if (movieList.get(j).getYear().compareTo(movieList.get(index).getYear()) < 0) {
						index = j;
					}
				} else {
					if (movieList.get(j).getYear().compareTo(movieList.get(index).getYear()) > 0) {
						index = j;
					}
				}

			}

			swapItem(movieList, index, i);
		}
	}

	/**
	 * Performing Bubble sort by paring the values and move the smaller one to the
	 * left side of the pair, do this through the whole list
	 * 
	 * @param movieList
	 * @param derection
	 */
	public static void bubbleSort(List<Movie> movieList, SortDirection derection) {
		boolean isSorted;

		for (int i = 0; i < movieList.size(); i++) {
			isSorted = true;

			for (int j = 1; j < (movieList.size() - i); j++) {
				if (SortDirection.ASC.equals(derection)) {
					if (movieList.get(j - 1).getYear().compareTo(movieList.get(j).getYear()) > 0) {
						swapItem(movieList, j - 1, j);
						isSorted = false;
					}
				} else {
					if (movieList.get(j - 1).getYear().compareTo(movieList.get(j).getYear()) < 0) {
						swapItem(movieList, j - 1, j);
						isSorted = false;
					}
				}
			}

			// stop the loop when all items have been sorted
			if (isSorted) {
				break;
			}

		}
	}

	/**
	 * Swapping 2 items in the list
	 * 
	 * @param movieList
	 * @param smallerIndex
	 * @param biggerIndex
	 */
	private static void swapItem(List<Movie> movieList, int smallerIndex, int biggerIndex) {
		Movie smaller = movieList.get(smallerIndex);
		movieList.set(smallerIndex, movieList.get(biggerIndex));
		movieList.set(biggerIndex, smaller);
	}
}