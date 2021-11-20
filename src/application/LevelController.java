package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import application.story.Riddle;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LevelController {
	private Riddle[][] riddles;
	private Integer level;
	private int riddleNumber;
	private final int AMOUNT_OF_LEVELS = 4;
	
	public LevelController() throws FileNotFoundException, IOException {
		this.level = null;
		this.riddleNumber = 0;
		this.riddles = new Riddle[AMOUNT_OF_LEVELS][2];
		Deque<Riddle> possibleRiddles = new ArrayDeque<>();
		InputStream in = getClass().getResourceAsStream("/text/riddles_and_stories.csv"); 
		String line;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			while((line = br.readLine()) != null) {
				String[] lineAsArray = line.split("@");
				possibleRiddles.addLast(new Riddle(lineAsArray[0], lineAsArray[1], lineAsArray[2], lineAsArray[3]));
			}
			for(int lvl = 0; lvl < riddles.length; lvl++) {
				for(int riddle = 0; riddle < riddles[0].length; riddle++) {
					riddles[lvl][riddle] = possibleRiddles.removeFirst();
				}
			}
		}
	}
	
	public Integer getLevel() {
		return this.level;
	}
	
	public int getRiddleNumber() {
		return this.riddleNumber;
	}
	
	public Riddle getRiddle() {
		return this.riddles[level][riddleNumber];
	}
	
	public void nextRiddle(Label healthLabel) {
		if(level == null) {
			level = 0;
		} else {
			if(level + 1 == AMOUNT_OF_LEVELS && riddleNumber + 1 == 2) {
				AchievementController.setLevelComplete(level);
				Label scoreLabel = (Label) healthLabel.getScene().lookup("#scoreValue");
				new GameWinController().show(scoreLabel.getText());
			} else if(riddleNumber < 1) {
				riddleNumber++;
			} else {
				riddleNumber = 0;
				AchievementController.setLevelComplete(level);
				level++;
				int currentHealth = Integer.valueOf(healthLabel.getText());
				healthLabel.setText(String.valueOf(currentHealth + 1));
			}
		}
	}
	
	
	public Parent showGameOver() {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
			Main.currentScene.setRoot(root);
			return root;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
