package main.java.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The main entry-point of the game, called by the Launcher class and contains
 * common attributes used throughout the game.
 * 
 * @author Lewis/Harry
 *
 */
public class Main extends Application {
	public static Stage primaryStage;
	public static MediaPlayer mp;
	public static Boolean isSkipAllowed = false;
	public static Scene currentScene = null;
	public static int textSize = 16;
	public static String currentGameVersion = "1.0.0";
	
	/**
	 * Starts the game, loads the music and menu and sets the size, title and game icon.
	 * 
	 */
	@Override
	public void start(Stage primaryStage){
		try {
			Media gameMusic = new Media(this.getClass().getResource("/assets/audio/music.mp3").toString());
			mp = new MediaPlayer(gameMusic);
			Main.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			currentScene = new Scene(root, 1366, 768);
			primaryStage.setScene(currentScene);
			primaryStage.setTitle("Escape the Island");
			primaryStage.getIcons().add(new Image("/assets/img/game_icon.png"));
			primaryStage.setFullScreen(false);
			primaryStage.setResizable(false);
			primaryStage.show();
			mp.play();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("A fatal error has occurred");
			alert.setHeaderText("A fatal error has occurred");
			alert.setContentText("We are unable to load the game. Try closing and restarting the game.");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	/**
	 * Defines if skipping riddles is allowed (debug only) and launches the game.
	 * @param args Any relevant arguments called when launching the game
	 */
	public static void main(String[] args) {
		isSkipAllowed = true;
		launch(args);
	}
}
