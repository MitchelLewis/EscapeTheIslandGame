package main.java.application;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.java.application.commands.CommandController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

public class GameController {
	@FXML
	TextArea outputText;
	@FXML
	TextField inputText;
	@FXML
	Label heartAmount;
	@FXML
	Label hintAmount;
	@FXML
	Button nextCommandButton;
	@FXML
	Button answerCommandButton;
	@FXML
	Button hintCommandButton;
	@FXML
	Button exitButton;
	@FXML
	HBox answerSection;
	@FXML
	Button submtAnswerButton;
	private LevelController levelController;
	private CommandController commandController;
	
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(outputText, inputText, nextCommandButton, answerCommandButton, hintCommandButton, exitButton, answerSection, submtAnswerButton);
		try {
			levelController = new LevelController();
			commandController = new CommandController(levelController, Main.isSkipAllowed);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Stage stage = (Stage) outputText.getScene().getWindow();
			stage.close();
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) outputText.getScene().getWindow();
			stage.close();
		}
		
		outputText.setText("Welcome to Escape The Island.\r\n"
				+ "\r\n"
				+ "Before you get started on this magical adventure we need to make sure you know how to play.\r\n"
				+ "\r\n"
				+ "You can type in the text box at the bottom of your window, it takes simple commands like, \"help\" or \"quit\".\r\n"
				+ "\r\n"
				+ "You can progress in the game by answering a series of riddles on each level before reaching the strange 'X' marked on the map.\r\n"
				+ "\r\n"
				+ "Answering riddles is simple! All you need to do is type, \"answer\" followed by a space and your guess in double quotes (i.e. \"guess\").\r\n"
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
				+ "If you need to review the commands used to interact with this game, then either type \"help\" in the command box or click the question mark in the top right corner.\r\n"
				+ "\r\n"
				+ "Good luck.\r\n"
				+ "\r\n"
				+ "Type 'next' to continue.");
		
	}
	
	public void handleCommandEntry() {
		commandController.handleCommand(inputText.getText(), outputText);
	}
	
	public void showHelp() {
		CommandController.COMMANDS.get("help").handleCommand(outputText, null);
	}
	
	public void handleExit(MouseEvent event) {
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}
	
	public void handleNext() {
		commandController.handleCommand("next", outputText);
		nextCommandButton.setDisable(true);
		hintCommandButton.setDisable(false);
		answerCommandButton.setDisable(false);
		answerSection.setVisible(false);
	}
	
	public void handleHint() {
		commandController.handleCommand("hint", outputText);
		hintCommandButton.setDisable(true);
	}

	public void handleAnswer() {
		answerCommandButton.setDisable(true);
		answerSection.setVisible(true);
		inputText.requestFocus();
	}
	
	public void handleSubmitAnswer() {
		if(inputText.getText().equals("skip") && Main.isSkipAllowed) {
			commandController.handleCommand("skip", outputText);
		} else {
			commandController.handleCommand("answer \"" + inputText.getText() + "\"", outputText);
		}
	}


}