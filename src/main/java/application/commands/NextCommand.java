package main.java.application.commands;

import main.java.application.LevelController;
import main.java.application.story.StoryRiddle;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class NextCommand implements Command {
	private LevelController levelController;
	
	public NextCommand(LevelController levelController) {
		this.levelController = levelController;
	}

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
