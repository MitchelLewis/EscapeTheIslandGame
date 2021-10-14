package application.story;

public class Riddle {
	private String storyElement;
	private String riddleElement;
	private String answer;
	private String hint;
	private Boolean hasAnsweredCorrectly;
	private Boolean usedHint;
	
	public Riddle(String story, String riddle, String answer, String hint) {
		this.storyElement = story;
		this.riddleElement = riddle;
		this.answer = answer;
		this.hint = hint;
		this.hasAnsweredCorrectly = false;
		this.setUsedHint(false);
	}

	public String getStoryElement() {
		return storyElement;
	}

	public String getRiddleElement() {
		return riddleElement;
	}

	public String getAnswer() {
		return answer;
	}

	public String getHint() {
		return hint;
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
