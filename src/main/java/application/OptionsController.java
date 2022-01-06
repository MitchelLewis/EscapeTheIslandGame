package main.java.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

/**
 * Controller that sets the users preferences.
 * 
 * @author Lewis/Harry
 *
 */
public class OptionsController {
	@FXML
	Button muteBtn;
	
	@FXML
	Button backBtn;
	
	@FXML
	Slider volumeSlider;
	
	@FXML
	Slider textSizeSlider;

	@FXML
	Label volumeLbl;
	
	@FXML
	Label textLbl;
	
	boolean isMuted;
	
	/**
	 * Initialises the font size of all elements and sets the previous options selected, using
	 * default values if not.
	 * 
	 */
	@FXML
	public void initialize()
	{
		FontSetter.setFontForElements(muteBtn, backBtn, volumeLbl, textLbl);
		textSizeSlider.setValue(Main.textSize);
		volumeSlider.setValue(Main.mp.getVolume() * 100);
		isMuted = Main.mp.isMute();
	}
	
	/**
	 * Handles the muting/unmuting of the game music
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleMute(MouseEvent event){
		if(isMuted)
		{
			isMuted = !isMuted;
			Main.mp.setMute(isMuted);
		}
		else
		{
		
			isMuted = !isMuted;
			Main.mp.setMute(isMuted);
		}
	}
	
	/**
	 * Handles the muting/unmuting of the game music
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleVolume(MouseEvent event) {
		Main.mp.setVolume(volumeSlider.getValue()/100);

	}
	
	/**
	 * Handles changing the text size and sets it permanently
	 * 
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleTextSize(MouseEvent event) {
		FontSetter.setFontForElements(muteBtn, backBtn, volumeLbl, textLbl);
		Main.textSize = (int) textSizeSlider.getValue();
	}
	
	/**
	 * Handles the player wanting to go back to the menu
	 * 
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleBack(MouseEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Main.currentScene.setRoot(root);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("A fatal error has occurred");
			alert.setHeaderText("A fatal error has occurred");
			alert.setContentText("We are unable to load the menu. Try closing and restarting the game.");
			alert.showAndWait();
			e.printStackTrace();
		
		}
	}
}
