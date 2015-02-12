package com.ulan;

import java.io.Serializable;

public class Problem implements Serializable{
	private String question;
	private String answer;
	
	public Problem (String question, String answer){
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean isCorrect(String userAnswer){
		return userAnswer.trim().equalsIgnoreCase(answer);
	}

}
