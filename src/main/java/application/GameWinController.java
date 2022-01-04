package main.java.application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameWinController {
	@FXML
	Label finalScoreLabel;
	@FXML
	Button exitBtn;
	@FXML
	Button postScoreBtn;
	@FXML
	Label gameWinText;
	@FXML
	Button tryAgainBtn;
	@FXML
	Label achievementsUnlockedLabel;
	
	
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(exitBtn, postScoreBtn, gameWinText, tryAgainBtn);
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
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}
	
	public void handleSubmitScore(MouseEvent event) throws InterruptedException {
		Parent root;
		try {
			Stage stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("PostScore.fxml"));
			stage.setScene(new Scene(root, 1366, 768));
			stage.setFullScreen(false);
			stage.setResizable(false);
			stage.setTitle("Escape the Island");
			stage.getIcons().add(new Image("/assets/img/game_icon.png"));
			Label scoreLabel = (Label) root.lookup("#scoreValueLabel");
			scoreLabel.setText(finalScoreLabel.getText().split(" ")[1]);
			Thread.sleep(2000);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}
}
