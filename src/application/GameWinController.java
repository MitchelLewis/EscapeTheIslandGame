package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.commands.CommandController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameWinController {
	@FXML
	TextArea outputText;
	@FXML
	TextField inputText;
	@FXML
	Label finalScoreLabel;
	@FXML
	Button exitBtn;
	
	
	@FXML
	public void initialize() {
		
	}
	
	public void show(String finalScore) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
			Main.primaryStage.setScene(new Scene(root, 650, 500));
			Label finalScoreLabel = (Label) root.lookup("#finalScoreLabel");
			finalScoreLabel.setText("Score: " + finalScore);
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}
	
	
	public void handleExit(MouseEvent event) {
	    Stage stage = (Stage) exitBtn.getScene().getWindow();
	    stage.close();
	}
	
	public void handlePlay(MouseEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Game.fxml"));
			Main.primaryStage.setScene(new Scene(root, 650, 500));
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}
	}
}
