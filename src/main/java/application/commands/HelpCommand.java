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
		alert.setContentText("Here are the commands that will help you use and navigate the game:" + "\r\n"
				+ "help - shows the help menu\r\n"
				+ "quit - quits the game\r\n"
				+ "answer \"your answer here\" - submits your answer for the riddle\r\n"
				+ "next - continue through the game (where applicable)\r\n"
				+ "hint - provides a useful hint to solving the riddle");
		alert.showAndWait();
	}
}
