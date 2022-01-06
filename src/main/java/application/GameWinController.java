//Created by Lewis/Harry
package main.java.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
	
	/**
	 * Called on scene change, primarily sets the size of the nodes on screen.
	 */
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(exitBtn, postScoreBtn, gameWinText, tryAgainBtn);
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
	 * Handles when the player wishes to try and play the game again, it resets the achievements
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
	 * Handles loading the submit score window
	 * 
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleSubmitScore(MouseEvent event) {
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
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("A fatal error has occurred");
			alert.setHeaderText("A fatal error has occurred");
			alert.setContentText("We are unable to submit your score. Try closing and restarting the game.");
			alert.showAndWait();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}
}
