//Created by Lewis/Harry
package main.java.application.commands;

import java.util.HashMap;
import java.util.Map;

import main.java.application.LevelController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
/**
 * Used to map a string to a command object 
 * 
 * @author Lewis/Harry
 *
 */
public class CommandController {
	public static final Map<String, Command> COMMANDS = new HashMap<>();
	
	/**
	 * maps strings to a command then can be used later to call a specific command action
	 * 
	 * @param levelController needed for some commands to get the current level and get the answer/hint for a riddle
	 * @param isSkipAllowed if set to true skip command can be used in game to skip questions can be changed in main
	 */
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
	
	/**
	 * 
	 * @param command the command given by the user
	 * @param answer given by the user specifically used in the {@link AnswerCommand} class   
	 * @param gameOutput the output text area object used to get various elements for commannds and edit the output text
	 */
	public void handleCommand(String command, String answer, TextArea gameOutput) {
		if(COMMANDS.containsKey(command)) {
			COMMANDS.get(command).handleCommand(gameOutput, answer);
		}
	}
	
}
