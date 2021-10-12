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

public class LevelController {
	private Riddle[][] riddles;
	private Integer level;
	private int riddleNumber;
	private Deque<Riddle> possibleRiddles;
	
	public LevelController() throws FileNotFoundException, IOException {
		this.level = null;
		this.riddleNumber = 0;
		this.riddles = new Riddle[2][2];
		this.possibleRiddles = new ArrayDeque<>();
		File seedFile = new File(System.getProperty("user.dir") + "/src/assets/text/riddles_and_stories.csv");
		String line;
		try(
				FileReader fr = new FileReader(seedFile); 
				BufferedReader br = new BufferedReader(fr)) {
			while((line = br.readLine()) != null) {
				String[] lineAsArray = line.split(",");
				possibleRiddles.push(new Riddle(lineAsArray[0], lineAsArray[1], lineAsArray[2], lineAsArray[3]));
			}
			for(int lvl = 0; lvl < riddles.length; lvl++) {
				for(int riddle = 0; riddle < riddles[0].length; riddle++) {
					riddles[lvl][riddle] = possibleRiddles.pop();
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
	
	public void nextRiddle() {
		if(level == null) {
			level = 0;
		}
		if(riddleNumber < 2) {
			riddleNumber++;
		} else {
			riddleNumber = 0;
			level++;
		}
	}
}
