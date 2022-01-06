//Created by Lewis/Harry
package main.java.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import main.java.application.story.Riddle;
import main.java.application.story.StoryRiddle;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import java.lang.InterruptedException;

import javafx.scene.Parent;

/**
 * A controller that deals with the construction of the 4 levels of the game,
 * it reads all the stories and riddles and compiles them in random order ready
 * to be called.
 * 
 * @author Lewis/Harry
 *
 */
public class LevelController {
	private StoryRiddle[][] storiesAndRiddles;
	private Integer level;
	private int riddleNumber;
	private final int AMOUNT_OF_LEVELS = 4;
	private final int AMOUNT_OF_RIDDLES = 5;
	
	/**
	 * Reads all the stories and creates the StoryRiddle classes and then adds
	 * the Riddle's later. 
	 * 
	 * @throws FileNotFoundException If any of the dependent asset files can not be found
	 * @throws IOException If there is some permissions/general I/O issue
	 */
	public LevelController() throws FileNotFoundException, IOException {
		this.level = null;
		this.riddleNumber = 0;
		this.storiesAndRiddles = new StoryRiddle[AMOUNT_OF_LEVELS][AMOUNT_OF_RIDDLES];
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
	
	/**
	 * Takes the name of the file name where the riddles are stored and
	 * compiles a list of Riddle's, if a Riddle has multiple answers, it is stored
	 * as an n-size answer list, otherwise if the Riddle only has one answer it is stored
	 * as a list with one element
	 * 
	 * @param fileName File name where the riddles can be found.
	 * @return A list of Riddle's to be attached to each Story
	 * @throws IOException If there is an issue accessing the file provided
	 */
	private List<Riddle> readRiddlesFile(String fileName) throws IOException {
		InputStream riddlesFile = getClass().getResourceAsStream(fileName);
		List<Riddle> riddles = new ArrayList<>();
		String line;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(riddlesFile))) {
			while((line = br.readLine()) != null) {
				String[] lineAsArray = line.split("@");
				List<String> answers = Arrays.asList(lineAsArray[1].split(","));
				if(answers.size() == 0) {
					answers = Arrays.asList(lineAsArray[1]);
				}
				for(int i = 0; i < answers.size(); i++) {
					answers.set(i, answers.get(i).toLowerCase());
				}
				riddles.add(new Riddle(lineAsArray[0], answers, lineAsArray[2]));
			}
		}
		return riddles;
	}
	
	/**
	 * Given all riddles, this method selects a random Riddle from the list 
	 * and adds the Riddle to the StoryRiddle, removing the Riddle from the
	 * candidate list when its used.
	 *  
	 * @param riddles List of all Riddle candidates
	 * @param story StoryRiddle to assign the Riddle to
	 */
	private void setRiddleForStory(List<Riddle> riddles, StoryRiddle story) {
		Random rand = new Random();
		Riddle riddleToSet = riddles.get(rand.nextInt(riddles.size()));
		story.setRiddle(riddleToSet);
		riddles.remove(riddleToSet);
	}

	/**
	 * Gets the current level
	 * @return the integer value of the current level
	 */
	public Integer getLevel() {
		return this.level;
	}
	
	/**
	 * Gets the riddle number
	 * @return the integer value of the current riddle
	 */
	public int getRiddleNumber() {
		return this.riddleNumber;
	}
	
	/**
	 * Gets the current StoryRiddle
	 * @return the current StoryRiddle
	 */
	public StoryRiddle getRiddle() {
		return this.storiesAndRiddles[level][riddleNumber];
	}
	
	/**
	 * Gets the next Riddle in the sequence to load. If the player has
	 * completed the game, then the game win scene is loaded and achievements/score set.
	 * If not, the game continues to the next riddle/level.
	 * 
	 * @param healthLabel A Node used to query other Node's in the scene
	 */
	public void nextRiddle(Label healthLabel) {
		if(level == null) {
			level = 0;
		} else {
			if(level + 1 == AMOUNT_OF_LEVELS && riddleNumber + 1 == 5) {
				AchievementController.setLevelComplete(level);
				Label score = (Label) healthLabel.getScene().lookup("#scoreValue");
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));
					AchievementController.setAchievementsUnlocked(root);
					Label finalScoreLabel = (Label) root.lookup("#finalScoreLabel");
					finalScoreLabel.setText("Score: " + score.getText());	
					Main.currentScene.setRoot(root);
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("A fatal error has occurred");
					alert.setHeaderText("A fatal error has occurred");
					alert.setContentText("We are unable to load the next part of the game. Try closing and restarting the game.");
					alert.showAndWait();
					e.printStackTrace();
				}
			} else if(riddleNumber < 4) {
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
	
	/**
	 * Shows the game over screen
	 * 
	 * @return the Parent element that contains all Node's in the scene.
	 */
	public Parent showGameOver() {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
			Main.currentScene.setRoot(root);
			Main.primaryStage.setFullScreen(false);
			Main.primaryStage.setResizable(false);
			return root;
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("A fatal error has occurred");
			alert.setHeaderText("A fatal error has occurred");
			alert.setContentText("Try closing and restarting the game.");
			alert.showAndWait();
			return null;
		}

	}
}
