package test.java;

import static org.junit.Assert.*;

import main.java.application.GameOverController;
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
        stage.setScene(new Scene(root, 1920, 1080));
        stage.setOnHidden(e -> isClosed = true);
        stage.show();
    }

    @Test
    public void handleExitTest() {
    	clickOn("#exitBtn");
    	assertTrue(isClosed);
    }

}