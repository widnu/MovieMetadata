package com.imdb.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imdb.comparator.MovieBasicComparator;
import com.imdb.model.Movie;
import com.imdb.util.CSVReader;
import com.imdb.util.Constants;
import com.imdb.util.PrintUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GraphController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(GraphController.class);

	private static List<Movie> movieList = null;

	@FXML
	private BarChart barchartMovie;

	@FXML
	private Button btnGraphBackLanding;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			readMovieFile();
			createBarChart();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void createBarChart() {
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Title");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Gross");


        for(Movie movie : movieList) {
            
            if(movie.getYear() == 2016) {
                XYChart.Series dataSeries0 = new XYChart.Series();
                XYChart.Series dataSeries1 = new XYChart.Series();
                
            	dataSeries0.getData().add(new XYChart.Data(movie.getTitle(), movie.getBudget()));
            	dataSeries1.getData().add(new XYChart.Data(movie.getTitle(), movie.getGross()));
            	
            	barchartMovie.getData().add(dataSeries0);
            	barchartMovie.getData().add(dataSeries1);
            }
//            dataSeries.setName(movie.getYear().toString());

        }
//        XYChart.Series dataSeries1 = new XYChart.Series();
//        dataSeries1.setName("2014");
//
//        dataSeries1.getData().add(new XYChart.Data("Desktop", 567));
//        dataSeries1.getData().add(new XYChart.Data("Phone"  , 65));
//        dataSeries1.getData().add(new XYChart.Data("Tablet"  , 23));
//
//        barchartMovie.getData().add(dataSeries1);
//
//        XYChart.Series dataSeries2 = new XYChart.Series();
//        dataSeries2.setName("2015");
//
//        dataSeries2.getData().add(new XYChart.Data("Desktop", 540));
//        dataSeries2.getData().add(new XYChart.Data("Phone"  , 120));
//        dataSeries2.getData().add(new XYChart.Data("Tablet"  , 36));
//
//        barchartMovie.getData().add(dataSeries2);

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