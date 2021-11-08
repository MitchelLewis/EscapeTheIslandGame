package application.commands;

import application.AchievementController;
import application.LevelController;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class HintCommand implements Command {

	private LevelController levelController;
	
	public HintCommand(LevelController lvlController) {
		this.levelController = lvlController;
	}
	
	@Override
	public void handleCommand(TextArea gameOutput, String input) {
		String hintText = levelController.getRiddle().getHint();
		
		//get scene from text area then lookup the label from the scene
		Label hintAmountLbl = (Label) gameOutput.getScene().lookup("#hintAmount");
		
		//Value of label text converted to int
		int hintValue = Integer.parseInt(hintAmountLbl.getText());
		
		//Check hint for this question has not already been used
		if(levelController.getRiddle().getUsedHint() == false)
		{
			//check the value is not 0 else alert they have not hints left
			if(hintValue != 0)
			{
				AchievementController.noHintsUsed = false;
				gameOutput.appendText("\n" + hintText);
				//update hint label
				hintAmountLbl.setText(Integer.toString(hintValue - 1));
				levelController.getRiddle().setUsedHint(true);
			}
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION, "You have no hints left!");
				alert.showAndWait();
			}
		}
		else 
		{
			Alert alert = new Alert(AlertType.INFORMATION, "Only 1 hint for each riddle!");
			alert.showAndWait();
		}


		
	}

}
