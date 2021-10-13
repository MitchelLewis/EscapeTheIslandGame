package application.commands;

import application.LevelController;
import javafx.scene.control.TextArea;

public class HintCommand implements Command {

	private LevelController levelController;
	
	public HintCommand(LevelController lvlController) {
		this.levelController = lvlController;
	}
	
	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		String hintText = levelController.getRiddle().getHint();
		gameOutput.appendText("\n" + hintText);
	}

}
