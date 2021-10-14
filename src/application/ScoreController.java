package application;

import application.story.Riddle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScoreController {
	public void incrementScore(Riddle riddle, TextArea gameOutput) {
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
