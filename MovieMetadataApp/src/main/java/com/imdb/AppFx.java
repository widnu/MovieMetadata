package com.imdb;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imdb.controller.SearchController;
import com.imdb.util.Constants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppFx extends Application {

	private static final Logger logger = LoggerFactory.getLogger(AppFx.class);

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			// Read file fxml and draw interface.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Constants.FX_MAIN_SCENE));
			URL xmlUrl = getClass().getResource(Constants.FX_MAIN_SCENE);
			loader.setLocation(xmlUrl);
			Parent root = loader.load();
			
			primaryStage.setTitle("My Movie Application");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
