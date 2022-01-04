//Created by Lewis/Harry
package main.java.application;

import main.java.application.story.StoryRiddle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScoreController {
	public void incrementScore(StoryRiddle riddle, TextArea gameOutput) {
		Label scoreLbl = (Label) gameOutput.getScene().lookup("#scoreValue");
		int currentScore = Integer.parseInt(scoreLbl.getText());
		if(riddle.getUsedHint()) {
			currentScore += 50;
		} else {
			currentScore += 100;
		}
		scoreLbl.setText(Integer.toString(currentScore));
	}
}
