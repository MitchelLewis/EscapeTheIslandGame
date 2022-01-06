//Created by Lewis/Harry
package main.java.application.commands;

import javafx.scene.control.TextArea;
/**
 * Interface used by commands to take an input command then 
 * peform an action based upon the command give
 * 
 * @param gameOutput the text that is output to user in game, needed in case it needs to be edited via a command e.g. hintcommand
 * @param answer taken from the input field that the user types is used in {@link AnswerCommand}
 * @author Lewis/Harry
 *
 */
@FunctionalInterface
public interface Command {
	public void handleCommand(TextArea gameOutput, String answer);
}
