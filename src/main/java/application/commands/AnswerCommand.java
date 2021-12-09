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

public class AnswerCommand implements Command {
	private LevelController levelController;
	private List<String> healthDepletionResponses;
	private ScoreController scoreController;

	public AnswerCommand(LevelController levelController) {
		this.levelController = levelController;
		this.scoreController = new ScoreController();
		this.healthDepletionResponses = new ArrayList<>();
		this.healthDepletionResponses.add("You slip and hurt your leg.");
		this.healthDepletionResponses.add("You get bitten by a grass snake.");
		this.healthDepletionResponses.add("You step on a plank and it hits you in the face.");
		this.healthDepletionResponses.add("You think too hard and you get a migraine.");
	}

	@Override
	public void handleCommand(TextArea gameOutput, String answer) {
		StoryRiddle currentRiddle = levelController.getRiddle();
		if (currentRiddle.getRiddleElement().getAnswer().equalsIgnoreCase(answer)) {
			AchievementController.amountOfRiddlesAnsweredInARow++;
			if(AchievementController.amountOfRiddlesAnsweredInARow == 3) {
				AchievementController.amountOfRiddlesAnsweredInARow = 0;
				AchievementController.chainedRiddles = true;
			}
			gameOutput.appendText("\nSomething seems to be happening.\nType 'next' to continue...");
			currentRiddle.setHasAnsweredCorrectly(true);
			Button nextButton = (Button) gameOutput.getScene().lookup("#nextCommandButton");
			nextButton.setDisable(false);
			scoreController.incrementScore(currentRiddle, gameOutput);
		} else {
			if (currentRiddle.getRiddleElement().getAnswer().contains(answer)) {
				AchievementController.noRiddlesWrong = false;
				AchievementController.amountOfRiddlesAnsweredInARow = 0;
				gameOutput.appendText("\nA rumble occurs but nothing else happens!\n");
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
