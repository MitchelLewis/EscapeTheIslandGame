package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;


public class OptionsController {
	@FXML
	Button muteBtn;
	
	@FXML
	Button backBtn;
	
	@FXML
	Slider volumeSlider;

	boolean isMuted;
	
	@FXML
	public void initialize()
	{
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
	
	public void handleBack(MouseEvent event)
	{
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Base.fxml"));
			Main.primaryStage.setScene(new Scene(root, 650, 500));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
}
