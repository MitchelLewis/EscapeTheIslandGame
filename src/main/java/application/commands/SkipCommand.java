//Created by Lewis/Harry
package main.java.application.commands;

import main.java.application.LevelController;
import main.java.application.story.StoryRiddle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * If enabled in main allows the player to type skip to skip riddles
 * 
 * @author Lewis/Harry
 *
 */
public class SkipCommand implements Command {
	
	private LevelController levelController;
	
	public SkipCommand(LevelController levelController) {
		this.levelController = levelController;
	}
	/**
	 * Handles how the game moves onto the next level when the skip command is given
	 * 
	 * @param gameOutput used to get the healthLbl as it is needed to call levelController.nextRiddle then sets the gameOutput text to the next riddle
	 * @param input not used by this command
	 */
	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		levelController.getRiddle().setHasAnsweredCorrectly(true);
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
