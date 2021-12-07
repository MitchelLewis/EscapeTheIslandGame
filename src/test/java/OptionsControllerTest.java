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

public class OptionsControllerTest extends ApplicationTest {
			
	Boolean isClosed = false;
	Stage stage;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(
                getClass().getResource("../../main/java/application/Options.fxml"));
        this.stage = stage;
        Scene scene = new Scene(root, 1920, 1080);
        Main.currentScene = scene;
        Main.primaryStage = stage;
		Media gameMusic = new Media(this.getClass().getResource("../../assets/audio/music.mp3").toString());
		Main.mp = new MediaPlayer(gameMusic);
        stage.setScene(scene);
        stage.setOnHidden(e -> isClosed = true);
        stage.show();
    }
    
    @Test
    public void handleMuteTest() {
    	clickOn("#muteBtn");
    	assertTrue(Main.mp.isMute());
    	clickOn("#muteBtn");
    	assertFalse(Main.mp.isMute());
    }
    
    @Test
    public void handleVolumeTest() {
    	FxRobot robot = new FxRobot();
    	robot.moveTo("#volumeSlider");
    	robot.drag(MouseButton.PRIMARY).dropBy(-500, 0);
    	assertEquals(0, Main.mp.getVolume(), 0);
    }
   
    @Test
    public void handleBackTest() {
    	clickOn("#backBtn");
    	assertTrue(this.stage.getScene().lookup("#playBtn").isVisible());
    }
    
    @Test
    public void handleTextSizeTest() {
    	FxRobot robot = new FxRobot();
    	robot.moveTo("#textSizeSlider");
    	robot.drag(MouseButton.PRIMARY).dropBy(-500, 0);
    	assertEquals(10, Main.textSize, 0);
    }
}