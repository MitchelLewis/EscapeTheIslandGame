package application.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.LevelController;
import application.story.Riddle;
import javafx.scene.control.TextArea;

public class AnswerCommand implements Command {
	private LevelController levelController;
	
	public AnswerCommand(LevelController levelController) {
		this.levelController = levelController;
	}

	@Override
	public void handleCommand(TextArea gameOutput, String answer) {
		Riddle currentRiddle = levelController.getRiddle();
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(answer.replace("answer ", ""));
		while (m.find()) {
		  String answerInQuotes = m.group(1);
		  if(currentRiddle.getAnswer().equals(answerInQuotes)) {
			  gameOutput.appendText("\nSomething seems to be happening.\nType 'next' to continue...");
			  currentRiddle.setHasAnsweredCorrectly(true);
		  } else if(currentRiddle.getAnswer().contains(answerInQuotes)) {
			  gameOutput.appendText("\nA rumble occurs but nothing else happens!\n");
		  }
		}
	}

}
