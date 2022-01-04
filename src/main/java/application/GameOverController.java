package main.java.application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameOverController {
	@FXML
	Button tryAgainBtn;
	@FXML
	Button exitBtn;
	@FXML
	Label achievementsUnlockedLabel;
	
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(tryAgainBtn, exitBtn, achievementsUnlockedLabel);
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
}
