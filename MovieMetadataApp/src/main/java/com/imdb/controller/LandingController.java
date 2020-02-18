package com.imdb.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imdb.model.Movie;
import com.imdb.util.Constants;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * A landing page class to control the other scenes on the stage
 * @author widnu
 *
 */
public class LandingController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(LandingController.class);

	@FXML
	private Button btnGoSearchMovie;

	@FXML
	private Button btnGoDrawGraph;

	private Stage stage;
	private Parent root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// keep this blank
	}

	/**
	 * Handle on click event and go to search scene
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	protected void goSearchMovieMouseClick(MouseEvent event) throws Exception {
		stage = (Stage) btnGoSearchMovie.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Constants.FX_MAIN_SCENE));
		changeScene();
	}

	/**
	 * Handle on click event and go to graph scene
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	protected void goDrawGraphMouseClick(MouseEvent event) throws Exception {
		stage = (Stage) btnGoDrawGraph.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(Constants.FX_GRAPH_SCENE));
		changeScene();
	}

	/**
	 * Place the selected scene to the stage
	 */
	private void changeScene() {
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
