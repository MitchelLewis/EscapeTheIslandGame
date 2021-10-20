package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import application.story.Riddle;
import javafx.scene.control.Label;

public class LevelController {
	private Riddle[][] riddles;
	private Integer level;
	private int riddleNumber;
	
	public LevelController() throws FileNotFoundException, IOException {
		this.level = null;
		this.riddleNumber = 0;
		this.riddles = new Riddle[2][2];
		Deque<Riddle> possibleRiddles = new ArrayDeque<>();
		File seedFile = new File(System.getProperty("user.dir") + "/src/assets/text/riddles_and_stories.csv");
		String line;
		try(FileReader fr = new FileReader(seedFile); 
			BufferedReader br = new BufferedReader(fr)) {
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
			if(level + 1 == 2 && riddleNumber + 1 == 2) {
				Label scoreLabel = (Label) healthLabel.getScene().lookup("#scoreValue");
				new GameWinController().show(scoreLabel.getText());
			} else if(riddleNumber < 1) {
				riddleNumber++;
			} else {
				riddleNumber = 0;
				level++;
				int currentHealth = Integer.valueOf(healthLabel.getText());
				healthLabel.setText(String.valueOf(currentHealth + 1));
			}
		}
	}
}
