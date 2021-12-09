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
import org.testfx.api.FxRobot;

public class GameControllerTest extends ApplicationTest {
			
	Boolean isClosed = false;
	Stage stage;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Main.isSkipAllowed = true;
    	Parent root = FXMLLoader.load(
                getClass().getResource("../../main/java/application/Game.fxml"));
        this.stage = stage;
        Scene scene = new Scene(root, 1920, 1080);
        Main.currentScene = scene;
        Main.primaryStage = stage;
        stage.setScene(scene);
        stage.setOnHidden(e -> isClosed = true);
        stage.show();
    }
    
    @Test
    public void initializeTest() {
    	TextArea outputText = (TextArea) this.stage.getScene().lookup("#outputText");
    	assertTrue(outputText.isVisible());
    	assertTrue(outputText.getText().contains("Welcome to Escape The Island."));
    }


    @Test
    public void handleExitTest() {
    	clickOn("#exitButton");
    	assertTrue(isClosed);
    }
    
    @Test
    public void showHelpTest() {
    	clickOn("#helpCommand");
    	FxRobot robot = new FxRobot();
    	assertTrue(robot.listWindows().get(1).isFocused());
    }
    
    @Test
    public void handleNextTest() {
    	clickOn("#nextCommandButton");
    	assertFalse(this.stage.getScene().lookup("#answerCommandButton").isDisabled());
    	assertFalse(this.stage.getScene().lookup("#answerSection").isVisible());
    	assertFalse(this.stage.getScene().lookup("#hintCommandButton").isDisabled());
    	assertTrue(this.stage.getScene().lookup("#nextCommandButton").isDisabled());
    }

    @Test
    public void handleHintTest() {
    	TextArea outputText = (TextArea) this.stage.getScene().lookup("#outputText");
    	String outputBeforeHint = outputText.getText();
    	clickOn("#nextCommandButton");
    	Label hintAmountBefore = (Label) this.stage.getScene().lookup("#hintAmount");
    	assertEquals("3", hintAmountBefore.getText());
    	clickOn("#hintCommandButton");
    	assertTrue(this.stage.getScene().lookup("#hintCommandButton").isDisabled());
    	Label hintAmountAfter = (Label) this.stage.getScene().lookup("#hintAmount");
    	String outputAfterHint = outputText.getText();
    	assertNotEquals(outputAfterHint, outputBeforeHint);
    	assertEquals("2", hintAmountAfter.getText());
    }
    
    @Test
    public void handleAnswerTest() {
    	clickOn("#nextCommandButton");
    	clickOn("#answerCommandButton");
    	assertTrue(this.stage.getScene().lookup("#answerSection").isVisible());
    	assertTrue(this.stage.getScene().lookup("#answerCommandButton").isDisabled());
    	assertTrue(this.stage.getScene().lookup("#inputText").isVisible());
    }

    @Test
    public void answerWrongTest() {
    	clickOn("#nextCommandButton");
    	clickOn("#answerCommandButton");
    	assertTrue(this.stage.getScene().lookup("#answerSection").isVisible());
    	Label healthAmountBefore = (Label) this.stage.getScene().lookup("#heartAmount");
    	assertEquals("3", healthAmountBefore.getText());
    	clickOn("#inputText");
    	write("x");
    	clickOn("#submtAnswerButton");
    	Label healthAmountAfter = (Label) this.stage.getScene().lookup("#heartAmount");
    	assertEquals("2", healthAmountAfter.getText());
    }
    
    @Test
    public void gameOverTest() {
    	clickOn("#nextCommandButton");
    	clickOn("#answerCommandButton");
    	assertTrue(this.stage.getScene().lookup("#answerSection").isVisible());
    	Label healthAmountBefore = (Label) this.stage.getScene().lookup("#heartAmount");
    	assertEquals("3", healthAmountBefore.getText());
    	clickOn("#inputText");
    	write("x");
    	clickOn("#submtAnswerButton");
    	Label healthAmountAfter = (Label) this.stage.getScene().lookup("#heartAmount");
    	assertEquals("2", healthAmountAfter.getText());
    	clickOn("#submtAnswerButton");
    	healthAmountAfter = (Label) this.stage.getScene().lookup("#heartAmount");
    	assertEquals("1", healthAmountAfter.getText());
    	clickOn("#submtAnswerButton");
    	assertTrue(this.stage.getScene().lookup("#gameOverImg").isVisible());
    }
}
