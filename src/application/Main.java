package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("Base.fxml"));
			primaryStage.setScene(new Scene(root, 650, 500));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Escape the Island");
			primaryStage.getIcons().add(new Image("/assets/img/game_icon.png"));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
