//Created by Lewis/Harry
package main.java.application.commands;

import main.java.application.LevelController;
import main.java.application.story.StoryRiddle;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
/**
 * When the next command is given handles moving to the next riddle and displaying it
 * 
 * @author Lewis/Harry
 *
 */
public class NextCommand implements Command {
	private LevelController levelController;
	
	public NextCommand(LevelController levelController) {
		this.levelController = levelController;
	}
	
	/**
	 * If the riddle has been answered correctly then showRiddle is called else an alert is 
	 * displayed to the player to answer the riddle correctly first 
	 * 
	 * @param gameOutput used to display the contents of the next riddle this includes story text and the riddle itself
	 * @param input not used by this command
	 */
	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		try {
			StoryRiddle currentRiddle = levelController.getRiddle();
			if(currentRiddle.getHasAnsweredCorrectly()) {
				showRiddle(gameOutput);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "You must enter an answer before continuing.");
				alert.showAndWait();
			}
		} catch (NullPointerException e) {
			showRiddle(gameOutput);
		}
	}
	
	/**
	 * Handles how the game moves onto the next level when the next command is given
	 * 
	 * @param gameOutput used to get the healthLbl as it is needed to call levelController.nextRiddle then sets the gameOutput text to the next riddle
	 */
	private void showRiddle(TextArea gameOutput) {
		Label healthLabel = (Label) gameOutput.getScene().lookup("#heartAmount");
		levelController.nextRiddle(healthLabel);
		StoryRiddle nextRiddle = levelController.getRiddle();
		StringBuilder sb = new StringBuilder();
		sb.append(nextRiddle.getStoryElement());
		sb.append("\n-------------------\n");
		sb.append(nextRiddle.getRiddleElement().getRiddle());
		gameOutput.setText(sb.toString());
	}
	
}
