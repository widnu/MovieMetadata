package com.imdb.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imdb.model.Movie;
import com.imdb.service.SearchService;
import com.imdb.service.StatisticService;
import com.imdb.util.CSVReader;
import com.imdb.util.Constants;
import com.imdb.util.PrintUtil;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SearchController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@FXML
	private ListView listViewMovies;

	@FXML
	private TextField mSearchMovieName;

	@FXML
	private Label mTitle;
	@FXML
	private Label mDuration;
	@FXML
	private Label mLanguage;
	@FXML
	private Label mContentRating;
	@FXML
	private Label mDirector;
	@FXML
	private Label mBudget;
	@FXML
	private Label mPlotKeywords;
	@FXML
	private Label mYear;
	@FXML
	private Label mGenres;
	@FXML
	private Label mCountry;
	@FXML
	private Hyperlink mFacebookLink;
	@FXML
	private Label mImdbScore;
	@FXML
	private Label mGross;
	@FXML
	private Label mActors;

	@FXML
	private Label mCount;
	@FXML
	private Label mModeYear;
	@FXML
	private Label mMaxBudget;
	@FXML
	private Label mMinBudget;
	@FXML
	private Label mMaxGross;
	@FXML
	private Label mMinGross;

	@FXML
	private Label mMedianBudget;
	@FXML
	private Label mMeanBudget;

	@FXML
	private Label mMedianGross;
	@FXML
	private Label mMeanGross;

	private NumberFormat formatter;

	private static List<Movie> movieList = null;

	protected ListProperty<String> listProperty = new SimpleListProperty<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		formatter = NumberFormat.getCurrencyInstance();

		readMovieFile();

		// initial the movie listview
		List<String> titleList = movieList.stream().map(x -> x.getTitle()).collect(Collectors.toList());
		listProperty.set(FXCollections.observableArrayList(titleList));
		listViewMovies.itemsProperty().bind(listProperty);

		// shows movies' statistics for all movies
		StatisticService statService = new StatisticService();
		try {
			displayStatisticFields(statService, movieList);
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
		}

		// on change event to filter the movie list
		mSearchMovieName.textProperty().addListener((observable, oldValue, newValue) -> {

			// shows movies' statistics for the filtered movies
			List<Movie> filteredMovieList = movieList.stream()
					.filter(x -> (x.getTitle().toLowerCase().startsWith(newValue))).collect(Collectors.toList());

			if (!filteredMovieList.isEmpty()) {

				try {
					displayStatisticFields(statService, filteredMovieList);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}

			// display only the filtered movies in the list view
			List<String> filteredTitleList = filteredMovieList.stream().map(x -> x.getTitle())
					.collect(Collectors.toList());

			listProperty.set(FXCollections.observableArrayList(filteredTitleList));
		});
	}

	private void displayStatisticFields(StatisticService service, List<Movie> movieList) throws Exception {
		mCount.setText(String.valueOf(movieList.size()));
		mModeYear.setText(String.valueOf(service.findModeYear(movieList)));

		mMaxBudget.setText(formatter.format(service.findMaxBudget(movieList)));
		mMaxGross.setText(formatter.format(service.findMaxGross(movieList)));
		mMinBudget.setText(formatter.format(service.findMinBudget(movieList)));
		mMinGross.setText(formatter.format(service.findMinGross(movieList)));

		mMeanBudget.setText(formatter.format(service.findMeanBudget(movieList)));
		mMeanGross.setText(formatter.format(service.findMeanGross(movieList)));
		mMedianBudget.setText(formatter.format(service.findMedianBudget(movieList)));
		mMedianGross.setText(formatter.format(service.findMedianGross(movieList)));
	}

	@FXML
	protected void handleListViewMouseClick(MouseEvent event) throws Exception {
		logger.debug("clicked on " + listViewMovies.getSelectionModel().getSelectedItem());

		String movieTitle = (String) listViewMovies.getSelectionModel().getSelectedItem();
		Movie movie = searchMovie(movieTitle);
		displayMovieDetail(movie);
	}

	private Movie searchMovie(String movieTitle) throws Exception {
		SearchService service = new SearchService();
		Movie movie = service.searchByTitle(movieList, movieTitle);
		return movie;
	}

	private void displayMovieDetail(Movie movie) throws Exception {
		mTitle.setText(movie.getTitle());
		mDuration.setText(String.valueOf(movie.getDuration()));
		mLanguage.setText(movie.getLanguage());
		mContentRating.setText(movie.getContentRating());
		mDirector.setText(movie.getDirector().getFullname());
		mYear.setText(String.valueOf(movie.getYear()));
		mCountry.setText(movie.getCountry());
		mFacebookLink.setText(movie.getImdbLink());
		mImdbScore.setText(String.valueOf(movie.getImdbScore().doubleValue()));

		mBudget.setText(formatter.format(movie.getBudget().doubleValue()));
		mGross.setText(formatter.format(movie.getGross().doubleValue()));

		mGenres.setText(String.join(", ", movie.getGenres()));
		mPlotKeywords.setText(String.join(", ", movie.getPlotKeywords()));

		List<String> actorNameList = movie.getCrewList().stream().map(x -> x.getFullname())
				.collect(Collectors.toList());
		mActors.setText(String.join(", ", actorNameList));
	}

	private static void readMovieFile() {
		CSVReader csvReader = new CSVReader();
		movieList = new ArrayList<Movie>();

		csvReader.read(Constants.MOVIE_METADATA, movieList);

		PrintUtil.printMovieYear(movieList);
	}
}
