package application.commands;

import application.LevelController;
import application.story.Riddle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SkipCommand implements Command {
	
	private LevelController levelController;
	
	public SkipCommand(LevelController levelController) {
		this.levelController = levelController;
	}

	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		levelController.getRiddle().setHasAnsweredCorrectly(true);
		Label healthLabel = (Label) gameOutput.getScene().lookup("#heartAmount");
		levelController.nextRiddle(healthLabel);
		Riddle nextRiddle = levelController.getRiddle();
		StringBuilder sb = new StringBuilder();
		sb.append(nextRiddle.getStoryElement());
		sb.append("\n-------------------\n");
		sb.append(nextRiddle.getRiddleElement());
		gameOutput.setText(sb.toString());
	}

}
