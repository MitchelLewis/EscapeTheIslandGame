package main.java.application.story;

import java.util.List;

/**
 * Models the Riddle element, including the riddle itself, possible answers to the riddle along
 * with the hint and state.
 * 
 * @author Lewis/Harry
 *
 */
public class Riddle {
	private String riddleElement;
	private List<String> answers;
	private String hint;
	
	/**
	 * Constructs an instance of a Riddle, taking in the riddle itself, the answers and the hint
	 * 
	 * @param riddle The riddle itself
	 * @param answers List of possible answers
	 * @param hint The hint that the player can request
	 */
	public Riddle(String riddle, List<String> answers, String hint) {
		this.riddleElement = riddle;
		this.answers = answers;
		this.hint = hint;
	}

	/**
	 * Gets the riddle
	 * 
	 * @return The riddle
	 */
	public String getRiddle() {
		return riddleElement;
	}

	/**
	 * Returns all the possible answers
	 * 
	 * @return all the possible answers
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * Gets the hint of the riddle
	 * 
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}
}
