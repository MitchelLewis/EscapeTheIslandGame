package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
		this.httpClient = HttpClient.newHttpClient();
	}
	
	public void handleSubmitScore(MouseEvent event) {
		String name = nameEntry.getText();
		String score = scoreLabel.getText();
		sendScoreToWebsite(name, score, nameEntry.getScene());
	}
	
	private void sendScoreToWebsite(String name, String score, Scene scene) {
	    String body = "{\"name\": \"" + name + "\", \"score\": \"" + score + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
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
