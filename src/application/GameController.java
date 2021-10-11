package application;

import application.commands.CommandController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GameController {
	@FXML
	TextArea outputText;
	@FXML
	TextField inputText;
	private final CommandController commandController = new CommandController();
	
	@FXML
	public void initialize() {
		outputText.setText("Welcome to Escape The Island.\r\n"
				+ "\r\n"
				+ "Before you get started on this magical adventure we need to make sure you know how to play.\r\n"
				+ "\r\n"
				+ "You can type in the text box at the bottom of your window, it takes simple commands like, \"help\" or \"quit\".\r\n"
				+ "\r\n"
				+ "You can progress in the game by answering a series of riddles on each level before reaching the strange 'X' marked on the map.\r\n"
				+ "\r\n"
				+ "Answering riddles is simple! All you need to do is type, \"answer\" followed by a space and your guess.\r\n"
				+ "\r\n"
				+ "If you complete the level, you'll be awarded with an extra life and you'll continue making your way to 'X'.\r\n"
				+ "\r\n"
				+ "If you are wrong however, a life will be removed!\r\n"
				+ "\r\n"
				+ "Throughout the game your health can be seen by the red heart at the top right of the screen.\r\n"
				+ "\r\n"
				+ "If you are stuck on any riddle, you can request a hint that will help you to solve the riddle.\r\n"
				+ "\r\n"
				+ "Riddles are indicated by the book at the top right of the screen.\r\n"
				+ "\r\n"
				+ "Good luck.\r\n"
				+ "\r\n"
				+ "Type 'next' to continue.");
	}
	
	public void handleCommandEntry() {
		commandController.handleCommand(inputText.getText(), outputText);
	}
}
