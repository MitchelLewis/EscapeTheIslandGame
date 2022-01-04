//Created by Lewis/Harry
package test.java;

import static org.junit.Assert.*;

import main.java.application.ScoreController;
import main.java.application.Main;
import main.java.application.story.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javafx.scene.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import java.lang.InterruptedException;
import javafx.application.Platform;

public class ScoreControllerTest extends ApplicationTest {
			
	Stage stage;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(
                getClass().getResource("../../main/java/application/Game.fxml"));
        this.stage = stage;
        Scene scene = new Scene(root, 1366, 768);
        Main.currentScene = scene;
        Main.primaryStage = stage;
        stage.setScene(scene);
        stage.show();
    }
    
    @Test
    public void incrementScoreNoUsedHintTest() {
    	ScoreController controller = new ScoreController();
    	StoryRiddle riddleNoUsedHint = new StoryRiddle("story1", new Riddle("riddle", "answer", "hint"));
    	TextArea outputText = (TextArea) this.stage.getScene().lookup("#outputText");
    	// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
    	Platform.runLater(
    	  () -> {
    		  controller.incrementScore(riddleNoUsedHint, outputText);
    		  Label scoreLbl = (Label) this.stage.getScene().lookup("#scoreValue");
	    	  assertEquals("100", scoreLbl.getText());
    	  }
    	);
    }

    @Test
    public void incrementScoreHintUsedTest() {
    	ScoreController controller = new ScoreController();
    	StoryRiddle riddleUsedHint = new StoryRiddle("story1", new Riddle("riddle", "answer", "hint"));
    	riddleUsedHint.setUsedHint(true);
    	TextArea outputText = (TextArea) this.stage.getScene().lookup("#outputText"); 
    	// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
    	Platform.runLater(
    	  () -> {
    		  controller.incrementScore(riddleUsedHint, outputText);
    		  Label scoreLbl = (Label) this.stage.getScene().lookup("#scoreValue");
	    	  assertEquals("50", scoreLbl.getText());
    	  }
    	);
    }
}