package main.java.application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MenuController {
	@FXML
	Button playBtn;
	@FXML
	Button optionsBtn;
	@FXML
	Button exitBtn;
	
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(playBtn, optionsBtn, exitBtn);
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
			Main.primaryStage.setFullScreen(true);
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
