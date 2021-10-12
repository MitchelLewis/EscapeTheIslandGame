package application.commands;

import application.LevelController;
import application.story.Riddle;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class NextCommand implements Command {
	private LevelController levelController;
	
	public NextCommand(LevelController levelController) {
		this.levelController = levelController;
	}

	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		try {
			Riddle currentRiddle = levelController.getRiddle();
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
		levelController.nextRiddle();
		Riddle nextRiddle = levelController.getRiddle();
		StringBuilder sb = new StringBuilder();
		sb.append(nextRiddle.getStoryElement());
		sb.append("\n-------------------\n");
		sb.append(nextRiddle.getRiddleElement());
		gameOutput.setText(sb.toString());
	}
	
}
