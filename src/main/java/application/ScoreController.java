package main.java.application;

import main.java.application.story.StoryRiddle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * A controller to increment the players score
 * 
 * @author Lewis/Harry
 *
 */
public class ScoreController {
	/**
	 * Increments the players score, if the user has used a hint - the score is increased by 50 otherwise
	 * it is increased by 100.
	 * 
	 * @param riddle The current riddle the user has answered
	 * @param gameOutput The output text Node used to query other elements on the scene
	 */
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
