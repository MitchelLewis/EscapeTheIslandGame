package test.java;

import static org.junit.Assert.*;

import main.java.application.LevelController;
import main.java.application.GameController;
import main.java.application.AchievementController;
import main.java.application.Main;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javafx.scene.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.*;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import java.lang.InterruptedException;
import org.testfx.api.FxRobot;

public class LevelControllerTest extends ApplicationTest {
			
	Stage stage;
	LevelController controller;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Main.isSkipAllowed = true;
    	FXMLLoader loader = new FXMLLoader(
                getClass().getResource("../../main/java/application/Game.fxml"));
        this.stage = stage;
        Scene scene = new Scene(loader.load(), 1366, 768);
        controller = ((GameController) loader.getController()).getLevelController();
        Main.currentScene = scene;
        Main.primaryStage = stage;
        stage.setScene(scene);
        stage.show();
    }
    
    @Test
    public void getRiddleNumberTest() {
    	assertEquals(0, controller.getRiddleNumber());
    }
    
    @Test
    public void nextRiddleChangeLevelOnInitTest() {
    	Label healthLabel = (Label) this.stage.getScene().lookup("#heartAmount");
    	controller.nextRiddle(healthLabel);
    	assertEquals(new Integer(0), controller.getLevel());
    }
    
    @Test
    public void nextRiddleTestWhenLessThan1() {
    	Label healthLabel = (Label) this.stage.getScene().lookup("#heartAmount");
    	controller.nextRiddle(healthLabel);
    	assertEquals(new Integer(0), controller.getLevel());
    	controller.nextRiddle(healthLabel);
    	assertEquals(1, controller.getRiddleNumber());
    }
    
    @Test
    public void nextRiddleAfterRiddlesCompleteNextLevelTest() {
    	Label healthLabel = (Label) this.stage.getScene().lookup("#heartAmount");
    	controller.nextRiddle(healthLabel);
    	assertEquals(new Integer(0), controller.getLevel());
    	controller.nextRiddle(healthLabel);
    	assertEquals(1, controller.getRiddleNumber());
       	// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
    	Platform.runLater(
    	  () -> {
    	    	controller.nextRiddle(healthLabel);
    	    	controller.nextRiddle(healthLabel);
    	    	controller.nextRiddle(healthLabel);
    	    	controller.nextRiddle(healthLabel);
    	    	final Label healthLabelAfterLevel = (Label) this.stage.getScene().lookup("#heartAmount");
    	    	assertEquals("4", healthLabelAfterLevel.getText());
    	    	assertTrue(AchievementController.level1Complete);
    	    	assertEquals(0, controller.getRiddleNumber());
    	    	assertEquals(new Integer(1), controller.getLevel());
    	  }
    	);
    	
    }
    
    @Test
    public void gameWinAfterAllLevelsCompleteTest() {
    	Label healthLabel = (Label) this.stage.getScene().lookup("#heartAmount");
       	// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
    	Platform.runLater(
    	  () -> {
    	    	for(int i = 0; i <= 20; i++) {
    	    		controller.nextRiddle(healthLabel);
    	    	}
    	    	assertTrue(this.stage.getScene().lookup("#gameWinImg").isVisible());
    	  }
    	);
    }
    
    @Test
    public void showGameOverTest() {
       	// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
    	Platform.runLater(
    	  () -> {
    		controller.showGameOver();
	    	assertTrue(this.stage.getScene().lookup("#gameOverImg").isVisible());
    	  }
    	);
    }
}
