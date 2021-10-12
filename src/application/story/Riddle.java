package application.story;

public class Riddle {
	private String storyElement;
	private String riddleElement;
	private String answer;
	private String hint;
	private Boolean hasAnsweredCorrectly;
	
	public Riddle(String story, String riddle, String answer, String hint) {
		this.storyElement = story;
		this.riddleElement = riddle;
		this.answer = answer;
		this.hint = hint;
		this.hasAnsweredCorrectly = false;
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
	
}
