package com.imdb.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imdb.model.Movie;
import com.imdb.util.CSVReader;
import com.imdb.util.Constants;
import com.imdb.util.PrintUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Draw Pie Chart of the Movie's Gross in 2016
 * @author widnu
 *
 */
public class GraphController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(GraphController.class);

	private static List<Movie> movieList = null;

	@FXML
	private PieChart piechart;

	@FXML
	private Button btnGraphBackLanding;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			readMovieFile();
			createPieChart();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createPieChart() {
		for (Movie movie : movieList) {
			if (movie.getYear() == 2016) {
				PieChart.Data slice = new PieChart.Data(movie.getTitle(), movie.getGross());
				piechart.getData().add(slice);
			}
		}

	}

	/**
	 * Go back to landing scene
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	protected void backLandingOnClick(MouseEvent event) throws Exception {
		Stage stage = (Stage) btnGraphBackLanding.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(Constants.FX_LANDING_SCENE));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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