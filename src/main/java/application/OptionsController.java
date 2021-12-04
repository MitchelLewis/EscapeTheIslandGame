package main.java.application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;


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
	
	@FXML
	public void initialize()
	{
		FontSetter.setFontForElements(muteBtn, backBtn, volumeLbl, textLbl);
		textSizeSlider.setValue(Main.textSize);
		volumeSlider.setValue(Main.mp.getVolume() * 100);
		isMuted =  Main.mp.isMute();
	}
	
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
	
	public void handleVolume(MouseEvent event)
	{
		Main.mp.setVolume(volumeSlider.getValue()/100);
	}
	
	public void handleTextSize(MouseEvent event)
	{
		FontSetter.setFontForElements(muteBtn, backBtn, volumeLbl, textLbl);
		Main.textSize = (int) textSizeSlider.getValue();
	}
	
	public void handleBack(MouseEvent event)
	{
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Main.currentScene.setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
}
