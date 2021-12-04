package main.java.application.commands;

import java.util.HashMap;
import java.util.Map;

import main.java.application.LevelController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class CommandController {
	public static final Map<String, Command> COMMANDS = new HashMap<>();
	
	public CommandController(LevelController levelController, Boolean isSkipAllowed) {
		COMMANDS.put("help", new HelpCommand());
		COMMANDS.put("quit", new QuitCommand());
		COMMANDS.put("next", new NextCommand(levelController));
		COMMANDS.put("answer", new AnswerCommand(levelController));
		COMMANDS.put("hint", new HintCommand(levelController));
		if(isSkipAllowed) {
			COMMANDS.put("skip", new SkipCommand(levelController));
		}
	}
	
	public void handleCommand(String command, TextArea gameOutput) {
		String strippedCommand = command.split(" ")[0];
		if(COMMANDS.containsKey(strippedCommand)) {
			COMMANDS.get(strippedCommand).handleCommand(gameOutput, command);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Invalid command, type 'help' to see valid commands.");
			alert.showAndWait();
		}
	}
	
}
