package main.java.application.story;

/**
 * Models a Story and a Riddle so that a story maps to one riddle
 * 
 * @author Lewis/Harry
 *
 */
public class StoryRiddle {
	private String storyElement;
	private Riddle riddleElement;
	private Boolean hasAnsweredCorrectly;
	private Boolean usedHint;
	
	/**
	 * Creates a StoryRiddle with only the story element populated
	 * @param story the story element
	 */
	public StoryRiddle(String story) {
		this.storyElement = story;
		this.riddleElement = null;
		this.hasAnsweredCorrectly = false;
		this.setUsedHint(false);
	}
	
	/**
	 * Overloaded constructor that initialises a story and a riddle at object
	 * creation
	 * @param story the story element
	 */
	public StoryRiddle(String story, Riddle riddle) {
		this.storyElement = story;
		this.riddleElement = riddle;
		this.hasAnsweredCorrectly = false;
		this.setUsedHint(false);
	}
	
	/**
	 * Sets the riddle element for the story
	 * 
	 * @param riddle the riddle itself
	 */
	public void setRiddle(Riddle riddle) {
		this.riddleElement = riddle;
	}

	/**
	 * Gets the story
	 * @return the story
	 */
	public String getStoryElement() {
		return storyElement;
	}

	/**
	 * Gets the riddle
	 * @return the riddle
	 */
	public Riddle getRiddleElement() {
		return riddleElement;
	}

	/**
	 * Gets the riddle
	 * @return the riddle
	 */
	public Boolean getHasAnsweredCorrectly() {
		return hasAnsweredCorrectly;
	}

	/**
	 * Sets the riddle to be correctly answered based on the parameter
	 * 
	 * @param hasAnsweredCorrectly parameter to set
	 */
	public void setHasAnsweredCorrectly(Boolean hasAnsweredCorrectly) {
		this.hasAnsweredCorrectly = hasAnsweredCorrectly;
	}

	/**
	 * Check if the hint has been used
	 * 
	 * @return boolean value indicating if the hint has been used
	 */
	public Boolean getUsedHint() {
		return usedHint;
	}

	/**
	 * Sets if the hint has been used
	 * 
	 * @param usedHint
	 */
	public void setUsedHint(Boolean usedHint) {
		this.usedHint = usedHint;
	}
	
}
