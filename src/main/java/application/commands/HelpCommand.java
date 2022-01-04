package main.java.application.commands;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class HelpCommand implements Command {
	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help menu");
		alert.setHeaderText("Help menu");
		alert.setContentText("Here is what each button does:" + "\r\n"
				+ "Next - shows the next part of the game\r\n"
				+ "Hint - provides a useful hint to solving the riddle\r\n"
				+ "Answer - shows the answer text box\r\n"
				+ "Submit - submits the answer\r\n"
				+ "Exit - quits the game\r\n");
		alert.showAndWait();
	}
}
