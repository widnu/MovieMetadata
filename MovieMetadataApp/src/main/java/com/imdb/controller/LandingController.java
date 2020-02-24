package com.imdb.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imdb.util.Constants;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * A landing page class to control the other scenes on the stage
 * 
 * @author widnu
 *
 */
public class LandingController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(LandingController.class);

	@FXML
	private Label lbLandingDesc;

	@FXML
	private TextFlow tfLandingFlow;

	@FXML
	private Button btnGoSearchMovie;

	@FXML
	private Button btnGoDrawGraph;

	private Stage stage;
	private Parent root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Text text_1 = new Text("IMDB Movie Dataset");

		text_1.setFill(Color.GREEN);
		text_1.setFont(Font.font("Verdana", 25));

		Text text_2 = new Text(Constants.MOVIE_LANDING_DESC);
		text_2.setFill(Color.BLUE);
		text_2.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));

		// add text to textflow
		tfLandingFlow.getChildren().add(text_1);
		tfLandingFlow.getChildren().add(text_2);

		// set text Alignment
		tfLandingFlow.setTextAlignment(TextAlignment.CENTER);

		// set line spacing
		tfLandingFlow.setLineSpacing(20.0f);
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
