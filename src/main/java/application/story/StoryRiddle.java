//Created by Lewis/Harry
package main.java.application.story;

public class StoryRiddle {
	private String storyElement;
	private Riddle riddleElement;
	private Boolean hasAnsweredCorrectly;
	private Boolean usedHint;
	
	public StoryRiddle(String story) {
		this.storyElement = story;
		this.riddleElement = null;
		this.hasAnsweredCorrectly = false;
		this.setUsedHint(false);
	}
	
	public StoryRiddle(String story, Riddle riddle) {
		this.storyElement = story;
		this.riddleElement = riddle;
		this.hasAnsweredCorrectly = false;
		this.setUsedHint(false);
	}
	
	public void setRiddle(Riddle riddle) {
		this.riddleElement = riddle;
	}

	public String getStoryElement() {
		return storyElement;
	}

	public Riddle getRiddleElement() {
		return riddleElement;
	}

	public Boolean getHasAnsweredCorrectly() {
		return hasAnsweredCorrectly;
	}

	public void setHasAnsweredCorrectly(Boolean hasAnsweredCorrectly) {
		this.hasAnsweredCorrectly = hasAnsweredCorrectly;
	}

	public Boolean getUsedHint() {
		return usedHint;
	}

	public void setUsedHint(Boolean usedHint) {
		this.usedHint = usedHint;
	}
	
}
