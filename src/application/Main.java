package application;
	
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	public static Stage primaryStage;
	public static MediaPlayer mp;
	public static Boolean isSkipAllowed = false;
	@Override
	public void start(Stage primaryStage){
		try {
			Media gameMusic = new Media(new File("src/assets/audio/Pirate_Crew.wav").toURI().toString());
			mp = new MediaPlayer(gameMusic);
			
			this.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("Base.fxml"));
			primaryStage.setScene(new Scene(root, 650, 500));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Escape the Island");
			primaryStage.getIcons().add(new Image("/assets/img/game_icon.png"));
			primaryStage.show();
			
			mp.play();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for(String arg: args) {
			if(arg.equals("allow-skip")) {
				isSkipAllowed = true;
			}
		}
		launch(args);
	}
}
