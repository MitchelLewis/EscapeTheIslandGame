//Created by Lewis/Harry
package main.java.application.commands;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Quits the game when it is called by the player
 * @author Lewis/Harry
 *
 */
public class QuitCommand implements Command {
	
	/**
	 * Handles closing the game window
	 * @param gameOutput used to get the game window to then close it
	 * @param input not used by this command
	 */
	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		Stage stage = (Stage) gameOutput.getScene().getWindow();
		stage.close();
	}

}
