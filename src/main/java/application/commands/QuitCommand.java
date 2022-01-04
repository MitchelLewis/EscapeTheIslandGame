//Created by Lewis/Harry
package main.java.application.commands;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class QuitCommand implements Command {

	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		Stage stage = (Stage) gameOutput.getScene().getWindow();
		stage.close();
	}

}
