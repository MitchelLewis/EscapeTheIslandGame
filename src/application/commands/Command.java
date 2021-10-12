package application.commands;

import javafx.scene.control.TextArea;

@FunctionalInterface
public interface Command {
	public void handleCommand(TextArea gameOutput, String answer);
}
