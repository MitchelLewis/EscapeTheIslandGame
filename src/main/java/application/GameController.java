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
				+ "To proceed to the next section of the game, you can do so by pressing 'Next' when available\r\n"
				+ "\r\n"
				+ "You can progress in the game by answering a series of riddles on each level before reaching the strange 'X' marked on the map.\r\n"
				+ "\r\n"
				+ "Answering riddles is simple! Click the 'Answer' button and an answer box will appear where you can type your guess, then click submit.\r\n"
				+ "\r\n"
				+ "If you complete the level, you'll be awarded with an extra life and you'll continue making your way to 'X'.\r\n"
				+ "\r\n"
				+ "If you are wrong however, a life will be removed!\r\n"
				+ "\r\n"
				+ "Throughout the game your health can be seen by the red heart at the bottom right of the screen.\r\n"
				+ "\r\n"
				+ "If you are stuck on any riddle, you can request a hint that will help you to solve the riddle.\r\n"
				+ "\r\n"
				+ "Hints remaining are indicated by the book at the bottom right of the screen.\r\n"
				+ "\r\n"
				+ "If you need any help using the game - click the question mark in the top right corner.\r\n"
				+ "\r\n"
				+ "Good luck.\r\n"
				+ "\r\n"
				+ "Click 'next' to continue.");
		
	}
	
	public void showHelp() {
		CommandController.COMMANDS.get("help").handleCommand(outputText, null);
	}
	
	public void handleExit(MouseEvent event) {
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}
	
	public void handleNext() {
		commandController.handleCommand("next", null, outputText);
		nextCommandButton.setDisable(true);
		hintCommandButton.setDisable(false);
		answerCommandButton.setDisable(false);
		answerSection.setVisible(false);
	}
	
	public void handleHint() {
		commandController.handleCommand("hint", null, outputText);
		hintCommandButton.setDisable(true);
	}

	public void handleAnswer() {
		answerCommandButton.setDisable(true);
		answerSection.setVisible(true);
		inputText.requestFocus();
	}
	
	public void handleSubmitAnswer() {
		if(inputText.getText().equals("skip") && Main.isSkipAllowed) {
			commandController.handleCommand("skip", null, outputText);
		} else {
			commandController.handleCommand("answer", inputText.getText(), outputText);
		}
	}
	
	public LevelController getLevelController() {
		return this.levelController;
	}


}
