package application.commands;

import javafx.scene.control.TextArea;

public class HelpCommand implements Command {
	@Override
	public void handleCommand(TextArea gameOutput) {
		gameOutput.setText("Commands:\r\n"
				+ "\r\n"
				+ "help - shows the help menu\r\n"
				+ "quit - quits the game\r\n"
				+ "answer \"your answer here\" - submits your answer for the riddle\r\n"
				+ "next - continue through the game (where applicable)\r\n"
				+ "hint - provides a useful hint to solving the riddle");
	}
}
