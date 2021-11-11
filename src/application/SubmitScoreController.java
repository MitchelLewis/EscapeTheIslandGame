package application;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SubmitScoreController {
	@FXML
	Label scoreLabel;
	@FXML
	TextField nameEntry;
	private HttpClient httpClient;
	
	@FXML
	public void initialize() {
		this.httpClient = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
	}
	
	public void handleSubmitScore(MouseEvent event) {
		String name = nameEntry.getText();
		String score = scoreLabel.getText();
		try {
			sendScoreToWebsite(name, score, nameEntry.getScene());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendScoreToWebsite(String name, String score, Scene scene) throws JsonProcessingException {
	    Map<String, String> body = new HashMap();
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
			System.out.println(response.body());
			System.out.println(response.statusCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
