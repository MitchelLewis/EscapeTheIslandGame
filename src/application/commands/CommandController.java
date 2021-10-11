package application.commands;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TextArea;

public class CommandController {
	public static final Map<String, Command> COMMANDS = new HashMap<>();
	
	public CommandController() {
		COMMANDS.put("help", new HelpCommand());
		COMMANDS.put("quit", new QuitCommand());
	}
	
	public void handleCommand(String command, TextArea gameOutput) {
		System.out.println(command);
		if(COMMANDS.containsKey(command)) {
			COMMANDS.get(command).handleCommand(gameOutput);
		} else {
			
		}
	}
	
}
