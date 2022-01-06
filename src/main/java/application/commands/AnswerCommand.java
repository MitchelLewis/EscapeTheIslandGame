//Created by Lewis/Harry
package main.java.application.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.application.AchievementController;
import main.java.application.LevelController;
import main.java.application.ScoreController;
import main.java.application.story.StoryRiddle;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
/**
 * Handles when the user gives an answer in game
 * 
 * 
 * @author Lewis/Harry
 *
 */
public class AnswerCommand implements Command {
	private LevelController levelController;
	private List<String> healthDepletionResponses;
	private ScoreController scoreController;
	
	/**
	 * Constructor for AnswerCommand
	 * @param levelController used to get the current level the player is on
	 */
	public AnswerCommand(LevelController levelController) {
		this.levelController = levelController;
		this.scoreController = new ScoreController();
		this.healthDepletionResponses = new ArrayList<>();
		this.healthDepletionResponses.add("You slip and hurt your leg.");
		this.healthDepletionResponses.add("You get bitten by a grass snake.");
		this.healthDepletionResponses.add("You step on a plank and it hits you in the face.");
		this.healthDepletionResponses.add("You think too hard and you get a migraine.");
	}
	
	/**
	 * Handles what happens when the user gives a correct incorrect or close answer.
	 * 
	 * if correct then text is appended the next button is enabled and the score is increased.
	 * If incorrect decreases health value and output a healthDepletionResponse message, if 0 health will show gameover screen.
	 * Also checks for achievements of chained riddles and no riddles wrong
	 * 
	 * @param gameOutput the output area is updated if the answer is incorrect,correct or close also used to find other elements on the page
	 * @param answer the answer given by the player
	 */
	@Override
	public void handleCommand(TextArea gameOutput, String answer) {
		StoryRiddle currentRiddle = levelController.getRiddle();
		if (currentRiddle.getRiddleElement().getAnswers().contains(answer.toLowerCase())) {
			AchievementController.amountOfRiddlesAnsweredInARow++;
			if(AchievementController.amountOfRiddlesAnsweredInARow == 3) {
				AchievementController.amountOfRiddlesAnsweredInARow = 0;
				AchievementController.chainedRiddles = true;
			}
			Button hintButton = (Button) gameOutput.getScene().lookup("#hintCommandButton");
			hintButton.setDisable(true);
			gameOutput.appendText("\nSomething seems to be happening.\nClick 'next' to continue...");
			currentRiddle.setHasAnsweredCorrectly(true);
			Button nextButton = (Button) gameOutput.getScene().lookup("#nextCommandButton");
			nextButton.setDisable(false);
			scoreController.incrementScore(currentRiddle, gameOutput);
		} else {
			for(String possibleAnswer: currentRiddle.getRiddleElement().getAnswers()) {
				if (possibleAnswer.contains(answer)) {
					AchievementController.noRiddlesWrong = false;
					AchievementController.amountOfRiddlesAnsweredInARow = 0;
					gameOutput.appendText("\nA rumble occurs but nothing else happens!\n");
					break;
				}
			}

			Label healthAmountLbl = (Label) gameOutput.getScene().lookup("#heartAmount");
			int healthAmount = Integer.parseInt(healthAmountLbl.getText());
			if(healthAmount <= 1) {
				AchievementController.noRiddlesWrong = false;
				AchievementController.amountOfRiddlesAnsweredInARow = 0;
				Parent root = levelController.showGameOver();
				AchievementController.setAchievementsUnlocked(root);
			} else {
				AchievementController.noRiddlesWrong = false;
				AchievementController.amountOfRiddlesAnsweredInARow = 0;
				int randomIdx = new Random().nextInt(this.healthDepletionResponses.size() - 1);
				String randomHealthDepletionMsg = this.healthDepletionResponses.get(randomIdx);
				gameOutput.appendText("\n"+ randomHealthDepletionMsg + "\n");
				healthAmount--;
				healthAmountLbl.setText("" + healthAmount);
			}
		}
	}

}
