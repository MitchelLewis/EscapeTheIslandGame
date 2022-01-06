package main.java.application;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller to handling submitting the score to the website
 * 
 * @author Lewis
 *
 */
public class SubmitScoreController {
	@FXML
	Label scoreKeyLabel;
	@FXML
	Label scoreValueLabel;
	@FXML
	TextField nameEntry;
	@FXML
	Label nameLabel;
	@FXML
	Button postScoreBtn;
	@FXML
	Label postScoreDescriptionLabel;
	
	private HttpClient httpClient;
	
	/**
	 * Initialises the font for all the elements in the scene and creates a new HttpClient
	 * 
	 */
	@FXML
	public void initialize() {
		FontSetter.setFontForElements(scoreKeyLabel, scoreValueLabel, nameEntry, postScoreBtn, nameLabel, postScoreDescriptionLabel);
		this.httpClient = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
	}
	
	/**
	 * Handles submitting the score to the website by first retrieving the players name and score.
	 * 
	 * @param event Mouse event that would be used to decide logic but is not used in this context.
	 */
	public void handleSubmitScore(MouseEvent event) {
		String name = nameEntry.getText();
		String score = scoreValueLabel.getText();
		try {
			sendScoreToWebsite(name, score, nameEntry.getScene());
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("A fatal error has occurred");
			alert.setHeaderText("A fatal error has occurred");
			alert.setContentText("We have been unable to submit your score to our website, please try again.");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends the score to the website by first creating a HTTP request body and building a POST request.
	 * It then sends the body and request to the website which synchronously handles the request and serves
	 * a response.
	 * In the case of a HTTP 200 OK response, the player is informed that the score has been submitted
	 * successfully, otherwise an error pop-up dialog is shown
	 * 
	 * @param name The players name
	 * @param score The players score
	 * @param scene The current scene
	 * @throws JsonProcessingException When the JSON request body can not be processed
	 */
	private void sendScoreToWebsite(String name, String score, Scene scene) throws JsonProcessingException {
	    Map<String, String> body = new HashMap<>();
	    body.put("name", name);
	    body.put("score", score);
	    ObjectMapper mapper = new ObjectMapper();
	    String requestBody = mapper
	            .writerWithDefaultPrettyPrinter()
	            .writeValueAsString(body);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://escape-the-island-game.herokuapp.com/score/submit"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .setHeader("Content-Type", "application/json")
                .build();
        try {
			HttpResponse<String> response = httpClient.send(request,
			        HttpResponse.BodyHandlers.ofString());
			if(response.statusCode() == 200) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Score posted");
				alert.setHeaderText("Score posted");
				alert.setContentText("We successfully posted your score to our website!\nView your score: https://escape-the-island-game.herokuapp.com/scores");
				alert.showAndWait();
				Stage stage = (Stage) scene.getWindow();
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed to submit score");
				alert.setHeaderText("Failed to submit score");
				alert.setContentText("Ooops, there was an error trying to post your score, please try again!");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Failed to submit score");
			alert.setHeaderText("Failed to submit score");
			alert.setContentText("Ooops, there was an error trying to post your score, please try again!");
			e.printStackTrace();
			alert.showAndWait();
		}
	}
}
