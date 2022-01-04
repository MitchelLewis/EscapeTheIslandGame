//Created by Lewis/Harry
package main.java.application.story;

import java.util.List;

public class Riddle {
	private String riddleElement;
	private List<String> answers;
	private String hint;
	private Boolean hasAnsweredCorrectly;
	private Boolean usedHint;
	
	public Riddle(String riddle, List<String> answers, String hint) {
		this.riddleElement = riddle;
		this.answers = answers;
		this.hint = hint;
		this.hasAnsweredCorrectly = false;
		this.usedHint = false;
	}

	public String getRiddle() {
		return riddleElement;
	}

	public List<String> getAnswers() {
		return answers;
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
