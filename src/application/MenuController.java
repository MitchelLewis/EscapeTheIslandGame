package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MenuController {
	@FXML
	Button playBtn;
	@FXML
	Button optionsBtn;
	@FXML
	Button exitBtn;
	
	
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
	
	public void handleOptions(MouseEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Options.fxml"));
			Main.primaryStage.setScene(new Scene(root, 650, 500));
		} catch (IOException e) {
			e.printStackTrace();
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();		
		}
	}
	
}
