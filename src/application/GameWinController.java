package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.commands.CommandController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameWinController {
	@FXML
	TextArea outputText;
	@FXML
	TextField inputText;
	@FXML
	Label finalScoreLabel;
	@FXML
	Button exitBtn;
	@FXML
	Button postScoreBtn;
	
	
	@FXML
	public void initialize() {
		
	}
	
	public void show(String finalScore) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
			Main.currentScene.setRoot(root);
			Label finalScoreLabel = (Label) root.lookup("#finalScoreLabel");
			finalScoreLabel.setText("Score: " + finalScore);
			AchievementController.setAchievementsUnlocked(root);
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
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
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}
	
	public void handleSubmitScore(MouseEvent event) {
		Parent root;
		try {
			Stage stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("EnterName.fxml"));
			stage.setScene(new Scene(root, 1024, 768));
			stage.setFullScreen(true);
			stage.setTitle("Escape the Island");
			stage.getIcons().add(new Image("/assets/img/game_icon.png"));
			Label scoreLabel = (Label) root.lookup("#scoreLabel");
			scoreLabel.setText(finalScoreLabel.getText().split(" ")[1]);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}
}
