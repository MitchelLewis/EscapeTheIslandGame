//Created by Lewis/Harry
package main.java.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Handles the interaction on the Game Over screen and loads the GameController scene if the
 * player wishes to try again.
 * 
 * @author Lewis/Harry
 *
 */
public class GameOverController {
	@FXML
	Button tryAgainBtn;
	@FXML
	Button exitBtn;
	@FXML
	Label achievementsUnlockedLabel;
	
	/**
	 * Called on scene change, primarily sets the size of the nodes on screen.
	 */
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(tryAgainBtn, exitBtn, achievementsUnlockedLabel);
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
}
