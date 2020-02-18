package com.imdb.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imdb.comparator.MovieBasicComparator;
import com.imdb.comparator.MovieBudgetComparator;
import com.imdb.comparator.MovieGrossComparator;
import com.imdb.comparator.MovieImdbScoreComparator;
import com.imdb.comparator.MovieTitleComparator;
import com.imdb.comparator.MovieYearComparator;
import com.imdb.enums.SortColumn;
import com.imdb.enums.SortDirection;
import com.imdb.model.Movie;
import com.imdb.model.SearchCriteria;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * To search and display movie's detail
 * 
 * @author widnu
 *
 */
public class SearchController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@FXML
	private ListView listViewMovies;

	@FXML
	private TextField txtSearchMovieName;
	@FXML
	private ComboBox<Integer> cbSearchYear;
	@FXML
	private ComboBox<String> cbSortBy;
	@FXML
	private ToggleGroup tgSortBy;
	@FXML
	private RadioButton rdSortAsc;
	@FXML
	private RadioButton rdSortDesc;

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

	@FXML
	private Button btnSearchBackLanding;

	private NumberFormat formatter;

	private static List<Movie> movieList = null;

	protected ListProperty<Integer> yearListProperty = new SimpleListProperty<>();

	protected ListProperty<String> movieListProperty = new SimpleListProperty<>();

	protected ListProperty<String> sortByListProperty = new SimpleListProperty<>();

	private SearchCriteria searchCriteria;

	private StatisticService statService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			formatter = NumberFormat.getCurrencyInstance();
			searchCriteria = new SearchCriteria();

			readMovieFile();
			initializeComponent();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * On click binding function to the listview
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	protected void handleListViewMouseClick(MouseEvent event) throws Exception {
		logger.debug("clicked on " + listViewMovies.getSelectionModel().getSelectedItem());

		String movieTitle = (String) listViewMovies.getSelectionModel().getSelectedItem();
		Movie movie = searchMovie(movieTitle);
		displayMovieDetail(movie);
	}

	/**
	 * Go back to landing scene
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	protected void backLandingOnClick(MouseEvent event) throws Exception {
		Stage stage = (Stage) btnSearchBackLanding.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Constants.FX_LANDING_SCENE));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Build all components in the screen
	 * 
	 * @throws Exception
	 */
	private void initializeComponent() throws Exception {
		// sort movie objects before doing the rest functions
		Collections.sort(movieList, new MovieBasicComparator());

		initializeMovieNameSearchInput();
		initializeYearCombobox();
		initializeSortByCombobox();
		initializeSortDirectionRadioGroup();
		initializeMovieListView();

		initializeMovieStatistic();
	}

	/**
	 * Initial the movie name search input textbox
	 */
	private void initializeMovieNameSearchInput() {
		// on change event to filter the movie list
		txtSearchMovieName.textProperty().addListener((observable, oldValue, newValue) -> {
			searchCriteria.setMovieName(newValue);
			filterSortMovie();
		});
	}

	/**
	 * Initial the year combobox filter
	 */
	private void initializeYearCombobox() {
		List<Integer> yearList = movieList.stream().map(x -> x.getYear()).distinct().sorted(Comparator.naturalOrder())
				.collect(Collectors.toList());
		yearListProperty.set(FXCollections.observableArrayList(yearList));
		cbSearchYear.itemsProperty().bind(yearListProperty);

		cbSearchYear.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue observable, Integer oldValue, Integer newValue) {
				if (newValue == null) {
					searchCriteria.setMovieYear(oldValue);
				} else {
					searchCriteria.setMovieYear(newValue);
				}

				filterSortMovie();
			}
		});
	}

	/**
	 * Initial the sortBy combobox
	 */
	private void initializeSortByCombobox() {
		List<String> sortByList = Stream.of(SortColumn.values()).map(SortColumn::name).sorted(Comparator.naturalOrder())
				.collect(Collectors.toList());
		sortByListProperty.set(FXCollections.observableArrayList(sortByList));
		cbSortBy.itemsProperty().bind(sortByListProperty);

		cbSortBy.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue observable, String oldValue, String newValue) {
				if (newValue == null) {
					searchCriteria.setSortBy(oldValue);
				} else {
					searchCriteria.setSortBy(newValue);
				}
				filterSortMovie();
			}
		});
	}

	/**
	 * Initial the sorting direction radio group
	 */
	private void initializeSortDirectionRadioGroup() {
		rdSortAsc.setUserData(SortDirection.ASC.toString());
		rdSortDesc.setUserData(SortDirection.DESC.toString());
		tgSortBy.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (tgSortBy.getSelectedToggle() != null) {
					searchCriteria.setSortDirection(tgSortBy.getSelectedToggle().getUserData().toString());
					filterSortMovie();
				}
			}
		});
	}

	/**
	 * Store the movies' filtered result into the listview
	 */
	private void initializeMovieListView() throws Exception {
		// initial the movie listview
		displayMovieListview(movieList);
		listViewMovies.itemsProperty().bind(movieListProperty);
	}

	/**
	 * Shows movies' statistics for all movies
	 */
	private void initializeMovieStatistic() throws Exception {
		statService = new StatisticService();
		displayStatisticFields(statService, movieList);
	}

	/**
	 * <ul>
	 * <li>Filter movies in the listview by title and/or year.</li>
	 * <li>Perform sorting with direction.</li>
	 * </ul>
	 */
	private void filterSortMovie() {
		try {
			List<Movie> filteredMovieList = movieList;

			if (searchCriteria.getMovieName() != null) {
				Predicate<Movie> movieNameStartsWith = x -> (x.getTitle().toLowerCase()
						.startsWith(searchCriteria.getMovieName()));
				filteredMovieList = filteredMovieList.stream().filter(movieNameStartsWith).collect(Collectors.toList());
			}

			if (searchCriteria.getMovieYear() != null) {
				Predicate<Movie> movieInYear = x -> (x.getYear()
						.intValue() == (searchCriteria.getMovieYear().intValue()));
				filteredMovieList = filteredMovieList.stream().filter(movieInYear).collect(Collectors.toList());
			}

			// do sorting by comparator
			Comparator<Movie> comparator = new MovieBasicComparator();
			if (searchCriteria.getSortBy() != null) {
				logger.debug("sort by = " + searchCriteria.getSortBy().toLowerCase());
				switch (searchCriteria.getSortBy().toLowerCase()) {
				case "title":
					comparator = new MovieTitleComparator();
					break;
				case "year":
					comparator = new MovieYearComparator();
					break;
				case "score":
					comparator = new MovieImdbScoreComparator();
					break;
				case "budget":
					comparator = new MovieBudgetComparator();
					break;
				case "gross":
					comparator = new MovieGrossComparator();
					break;
				default:
					comparator = new MovieBasicComparator();
					break;
				}
			}

			logger.debug("comparator = " + comparator.getClass().toString());

			// do sort direction
			if (searchCriteria.getSortDirection() != null) {
				logger.debug("sort direction = " + searchCriteria.getSortDirection());
				if (searchCriteria.getSortDirection().equals(SortDirection.DESC.toString())) {
					filteredMovieList = filteredMovieList.stream().sorted(comparator.reversed())
							.collect(Collectors.toList());
				} else {
					filteredMovieList = filteredMovieList.stream().sorted(comparator).collect(Collectors.toList());
				}
			}

			// display only the filtered movies in the list view
			displayMovieListview(filteredMovieList);

			displayStatisticFields(statService, filteredMovieList);

		} catch (Exception e) {
			logger.error("Cannot filter movies", e);
		}
	}

	/**
	 * Be triggered when the user choose a movie from the listview
	 * 
	 * @param movieTitle
	 * @return
	 * @throws Exception
	 */
	private Movie searchMovie(String movieTitle) throws Exception {
		SearchService service = new SearchService();
		Movie movie = service.searchByTitle(movieList, movieTitle);
		return movie;
	}

	/**
	 * Load movies into the listview component
	 * 
	 * @param movieList
	 * @throws Exception
	 */
	private void displayMovieListview(List<Movie> movieList) throws Exception {
		// display only the filtered movies in the list view
		List<String> filteredTitleList = movieList.stream().map(x -> x.getTitle()).collect(Collectors.toList());

		movieListProperty.set(FXCollections.observableArrayList(filteredTitleList));
	}

	/**
	 * Display the movie's details to the screen
	 * 
	 * @param movie
	 * @throws Exception
	 */
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

	/**
	 * Display the calculated values to the screen
	 * 
	 * @param service
	 * @param movieList
	 * @throws Exception
	 */
	private void displayStatisticFields(StatisticService service, List<Movie> movieList) throws Exception {
		if (!movieList.isEmpty()) {
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
	}

	/**
	 * Read the movies from csv file and put them into objects
	 */
	private static void readMovieFile() {
		CSVReader csvReader = new CSVReader();
		movieList = new ArrayList<Movie>();

		csvReader.read(Constants.MOVIE_METADATA, movieList);

		PrintUtil.printMovieYear(movieList);
	}
}
