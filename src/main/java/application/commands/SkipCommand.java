//Created by Lewis/Harry
package main.java.application.commands;

import main.java.application.LevelController;
import main.java.application.story.StoryRiddle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
		StoryRiddle nextRiddle = levelController.getRiddle();
		StringBuilder sb = new StringBuilder();
		sb.append(nextRiddle.getStoryElement());
		sb.append("\n-------------------\n");
		sb.append(nextRiddle.getRiddleElement().getRiddle());
		gameOutput.setText(sb.toString());
	}

}
