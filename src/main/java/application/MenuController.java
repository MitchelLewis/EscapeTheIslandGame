package main.java.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.stage.Stage;

/**
 * Controller to handle interaction and initialisation of the game menu, also checks
 * if the game has an available update at runtime.
 * 
 * @author Lewis/Harry
 *
 */
public class MenuController {
	@FXML
	Button playBtn;
	@FXML
	Button optionsBtn;
	@FXML
	Button exitBtn;
	
	HttpClient httpClient;
	
	/**
	 * Initialises the font of all elements in the scene and calls the website for the latest
	 * version of the game, if the game is outdated then a pop-up dialog is shown informing the
	 * user of an update for the game (no error handling as its not a critical error)
	 * 
	 */
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(playBtn, optionsBtn, exitBtn);
		this.httpClient = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://escape-the-island-game.herokuapp.com/current-game-version"))
                .build();
        try {
			HttpResponse<String> response = httpClient.send(request,
			        HttpResponse.BodyHandlers.ofString());
			if(response.statusCode() == 200) {
				String responseBody = (String) response.body();
				if(!responseBody.equals(Main.currentGameVersion)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("New version available");
					alert.setHeaderText("New version available");
					alert.setContentText("There is a new version of this game available on our website");
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handles closing the game
	 * 
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleExit(MouseEvent event) {
	    Stage stage = (Stage) exitBtn.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Handles when the player wishes to play the game, it resets the achievements
	 * and loads the main Game scene.
	 * 
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handlePlay(MouseEvent event) {
		AchievementController.resetAchievements();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Game.fxml"));
			Main.currentScene.setRoot(root);
			Main.primaryStage.setFullScreen(false);
			Main.primaryStage.setResizable(false);
			Main.currentScene.getStylesheets().add("main/java/application/application.css");
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("A fatal error has occurred");
			alert.setHeaderText("A fatal error has occurred");
			alert.setContentText("We are unable to load the game. Try closing and restarting the game.");
			alert.showAndWait();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
		
	}
	
	/**
	 * Handles when the player wishes to change the options and loads the Options scene.
	 * 
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleOptions(MouseEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Options.fxml"));
			Main.currentScene.setRoot(root);
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("A fatal error has occurred");
			alert.setHeaderText("A fatal error has occurred");
			alert.setContentText("We are unable to load the options screen. Try closing and restarting the game.");
			alert.showAndWait();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();		
		}
	}
	
}
