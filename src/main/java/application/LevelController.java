package main.java.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import main.java.application.story.Riddle;
import main.java.application.story.StoryRiddle;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LevelController {
	private StoryRiddle[][] storiesAndRiddles;
	private Integer level;
	private int riddleNumber;
	private final int AMOUNT_OF_LEVELS = 4;
	
	public LevelController() throws FileNotFoundException, IOException {
		this.level = null;
		this.riddleNumber = 0;
		this.storiesAndRiddles = new StoryRiddle[AMOUNT_OF_LEVELS][2];
		Deque<StoryRiddle> possibleStoriesAndRiddles = new ArrayDeque<>();
		InputStream in = getClass().getResourceAsStream("/text/stories.csv"); 
		List<Riddle> riddlesForLevel1 = readRiddlesFile("/text/level1Riddles.csv");
		List<Riddle> riddlesForLevel2 = readRiddlesFile("/text/level2Riddles.csv");
		List<Riddle> riddlesForLevel3 = readRiddlesFile("/text/level3Riddles.csv");
		List<Riddle> riddlesForLevel4 = readRiddlesFile("/text/level4Riddles.csv");
		String line;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			while((line = br.readLine()) != null) {
				String[] lineAsArray = line.split("@");
				possibleStoriesAndRiddles.addLast(new StoryRiddle(lineAsArray[0]));
			}
			for(int lvl = 0; lvl < storiesAndRiddles.length; lvl++) {
				for(int riddle = 0; riddle < storiesAndRiddles[0].length; riddle++) {
					storiesAndRiddles[lvl][riddle] = possibleStoriesAndRiddles.removeFirst();
					if(lvl == 0) {
						setRiddleForStory(riddlesForLevel1, storiesAndRiddles[lvl][riddle]);
					} else if(lvl == 1) {
						setRiddleForStory(riddlesForLevel2, storiesAndRiddles[lvl][riddle]);
					} else if(lvl == 2) { 
						setRiddleForStory(riddlesForLevel3, storiesAndRiddles[lvl][riddle]);
					} else if(lvl == 3) {
						setRiddleForStory(riddlesForLevel4, storiesAndRiddles[lvl][riddle]);
					}
					
				}
			}
		}
	}
	
	private List<Riddle> readRiddlesFile(String fileName) throws IOException {
		InputStream riddlesFile = getClass().getResourceAsStream(fileName);
		List<Riddle> riddles = new ArrayList<>();
		String line;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(riddlesFile))) {
			while((line = br.readLine()) != null) {
				String[] lineAsArray = line.split("@");
				riddles.add(new Riddle(lineAsArray[0], lineAsArray[1], lineAsArray[2]));
			}
		}
		return riddles;
	}
	
	private void setRiddleForStory(List<Riddle> riddles, StoryRiddle story) {
		Random rand = new Random();
		Riddle riddleToSet = riddles.get(rand.nextInt(riddles.size()));
		story.setRiddle(riddleToSet);
		riddles.remove(riddleToSet);
	}

	
	public Integer getLevel() {
		return this.level;
	}
	
	public int getRiddleNumber() {
		return this.riddleNumber;
	}
	
	public StoryRiddle getRiddle() {
		return this.storiesAndRiddles[level][riddleNumber];
	}
	
	public void nextRiddle(Label healthLabel) {
		if(level == null) {
			level = 0;
		} else {
			if(level + 1 == AMOUNT_OF_LEVELS && riddleNumber + 1 == 2) {
				AchievementController.setLevelComplete(level);
				Label score = (Label) healthLabel.getScene().lookup("#scoreValue");
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					AchievementController.setAchievementsUnlocked(root);
					Label finalScoreLabel = (Label) root.lookup("#finalScoreLabel");
					finalScoreLabel.setText("Score: " + score.getText());	
					Main.currentScene.setRoot(root);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
			Main.primaryStage.setFullScreen(true);
			return root;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
