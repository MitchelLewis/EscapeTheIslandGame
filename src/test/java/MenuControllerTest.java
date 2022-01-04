//Created by Lewis/Harry
package test.java;

import static org.junit.Assert.*;

import main.java.application.GameOverController;
import main.java.application.Main;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javafx.scene.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import java.lang.InterruptedException;
import org.testfx.api.FxRobot;


public class MenuControllerTest extends ApplicationTest {
			
	Boolean isClosed = false;
	Stage stage;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(
                getClass().getResource("../../main/java/application/Menu.fxml"));
        this.stage = stage;
        Scene scene = new Scene(root, 1366, 768);
        Main.currentScene = scene;
        Main.primaryStage = stage;
		Media gameMusic = new Media(this.getClass().getResource("../../assets/audio/music.mp3").toString());
		Main.mp = new MediaPlayer(gameMusic);
        stage.setScene(scene);
        stage.setOnHidden(e -> isClosed = true);
        stage.show();
    }
    
    @Test
    public void handlePlayTest() {
    	clickOn("#playBtn");
    	assertTrue(this.stage.getScene().lookup("#outputText").isVisible());
    }
    
    @Test
    public void handleOptionsTest() {
    	clickOn("#optionsBtn");
    	assertTrue(this.stage.getScene().lookup("#muteBtn").isVisible());
    }


    @Test
    public void handleExitTest() {
    	clickOn("#exitBtn");
    	assertTrue(isClosed);
    }
}