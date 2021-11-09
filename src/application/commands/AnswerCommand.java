package application.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.AchievementController;
import application.LevelController;
import application.Main;
import application.ScoreController;
import application.story.Riddle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		Riddle currentRiddle = levelController.getRiddle();
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(answer.replace("answer ", ""));
		while (m.find()) {
			String answerInQuotes = m.group(1);
			if (currentRiddle.getAnswer().equalsIgnoreCase(answerInQuotes)) {
				AchievementController.amountOfRiddlesAnsweredInARow++;
				if(AchievementController.amountOfRiddlesAnsweredInARow == 3) {
					AchievementController.amountOfRiddlesAnsweredInARow = 0;
					AchievementController.chainedRiddles = true;
				}
				gameOutput.appendText("\nSomething seems to be happening.\nType 'next' to continue...");
				currentRiddle.setHasAnsweredCorrectly(true);
				scoreController.incrementScore(currentRiddle, gameOutput);
			} else {
				if (currentRiddle.getAnswer().contains(answerInQuotes)) {
					AchievementController.noRiddlesWrong = false;
					AchievementController.amountOfRiddlesAnsweredInARow = 0;
					gameOutput.appendText("\nA rumble occurs but nothing else happens!\n");
				}
				Label healthAmountLbl = (Label) gameOutput.getScene().lookup("#heartAmount");
				int healthAmount = Integer.parseInt(healthAmountLbl.getText());
				if(healthAmount <= 1) {
					Parent root;
					try {
						AchievementController.noRiddlesWrong = false;
						AchievementController.amountOfRiddlesAnsweredInARow = 0;
						root = FXMLLoader.load(getClass().getResource("../GameOver.fxml"));
						Main.primaryStage.setScene(new Scene(root, 650, 500));
						AchievementController.setAchievementsUnlocked(root);
					} catch (IOException e) {
						e.printStackTrace();
					}
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

}
