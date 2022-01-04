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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import java.lang.InterruptedException;

public class GameOverControllerTest extends ApplicationTest {
			
	Boolean isClosed = false;
	Stage stage;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(
                getClass().getResource("../../main/java/application/GameOver.fxml"));
        this.stage = stage;
        Scene scene = new Scene(root, 1366, 768);
        Main.currentScene = scene;
        Main.primaryStage = stage;
        stage.setScene(scene);
        stage.setOnHidden(e -> isClosed = true);
        stage.show();
    }
    
    @Test
    public void handleTryAgainTest() {
    	clickOn("#tryAgainBtn");
    	assertTrue(this.stage.getScene().lookup("#outputText").isVisible());
    }


    @Test
    public void handleExitTest() {
    	clickOn("#exitBtn");
    	assertTrue(isClosed);
    }
    

}