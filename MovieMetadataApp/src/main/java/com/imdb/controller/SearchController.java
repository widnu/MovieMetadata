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
import com.imdb.util.CSVReader;
import com.imdb.util.Constants;
import com.imdb.util.PrintUtil;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class SearchController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@FXML
	private ListView listViewMovies;

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
	private Label mFacebookLink;
	@FXML
	private Label mImdbScore;
	@FXML
	private Label mGross;
	@FXML
	private Label mActors;

	private static List<Movie> movieList = null;

	protected ListProperty<String> listProperty = new SimpleListProperty<>();

	public SearchController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		readMovieFile();

		List<String> titleList = movieList.stream().map(x -> x.getTitle()).collect(Collectors.toList());

		listProperty.set(FXCollections.observableArrayList(titleList));

		listViewMovies.itemsProperty().bind(listProperty);

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
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
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
