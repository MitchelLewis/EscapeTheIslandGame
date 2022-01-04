//Created by Lewis/Harry
package main.java.application;

import java.io.IOException;

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


public class MenuController {
	@FXML
	Button playBtn;
	@FXML
	Button optionsBtn;
	@FXML
	Button exitBtn;
	
	HttpClient httpClient;
	
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
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void handleExit(MouseEvent event) {
	    Stage stage = (Stage) exitBtn.getScene().getWindow();
	    stage.close();
	}
	
	public void handlePlay(MouseEvent event) {
		AchievementController.resetAchievements();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Game.fxml"));
			Main.currentScene.setRoot(root);
			Main.primaryStage.setFullScreen(false);
			Main.primaryStage.setResizable(false);
			Main.currentScene.getStylesheets().add("main/java/application/application.css");
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
		
	}
	
	public void handleOptions(MouseEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Options.fxml"));
			Main.currentScene.setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();		
		}
	}
	
}
