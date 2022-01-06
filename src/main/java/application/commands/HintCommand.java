//Created by Lewis/Harry
package main.java.application.commands;

import main.java.application.AchievementController;
import main.java.application.LevelController;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

/**
 * When user ask for a hint it will get the hint from the currentLevel and output the hint to the gameOutput area
 * 
 * @author Lewis/Harry
 *
 */
public class HintCommand implements Command {

	private LevelController levelController;
	
	public HintCommand(LevelController lvlController) {
		this.levelController = lvlController;
	}
	
	/**
	 * When hint command is used first get the current levels riddle hint.
	 * If the command has already been used for this level then an alert will be presented
	 * if not check if they have any hints left and display an alert if they have no hints remaining
	 * if they have hints remaining then it will be appended to game output and the hintAmountLbl will be updated
	 * 
	 * @param gameOutput used to append the hint of the riddle to the game output also used to update the hint label value
	 * @param input not used by this command
	 */
	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		String hintText = levelController.getRiddle().getRiddleElement().getHint();

		// get scene from text area then lookup the label from the scene
		Label hintAmountLbl = (Label) gameOutput.getScene().lookup("#hintAmount");

		// Value of label text converted to int
		int hintValue = Integer.parseInt(hintAmountLbl.getText());

		// Check hint for this question has not already been used
		if (levelController.getRiddle().getUsedHint() == false) {
			// check the value is not 0 else alert they have not hints left
			if (hintValue != 0) {
				AchievementController.noHintsUsed = false;
				gameOutput.appendText("\n" + hintText);
				// update hint label
				hintAmountLbl.setText(Integer.toString(hintValue - 1));
				levelController.getRiddle().setUsedHint(true);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "You have no hints left!");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Only 1 hint for each riddle!");
			alert.showAndWait();
		}

	}

}
