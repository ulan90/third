package com.ulan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class QuizBean implements Serializable{
	
	private int currentProblem;
	private String correctAnswer;
	private int tries;
	private int score;
	private String requestRresponse = "";
	
	private ArrayList<Problem> problems = new ArrayList<Problem>(Arrays.asList(
		new Problem("What trademarked slogan describes Java development? Write once, ...", "run anywhere"),
		new Problem("What are the first 4 bytes of every class file (in hexadecimal)?", "CAFEBABE"),
		new Problem("What does this statement print? System.out.println(1+\"2\");", "12"),
		new Problem("Which Java keyword is used to define a subclass?", "extends"),
		new Problem("What was the original name of the Java programming language?", "Oak"),
		new Problem("Which java.util class describes a point in time?", "Date")
	));
	
	public String getQuestion(){
		return problems.get(currentProblem).getQuestion();
	}
	
	public String getAnswer(){ return correctAnswer; }
	
	public int getScore() { return score; }
	
	public String getResponse(){ return requestRresponse; }
	
	public void setResponse(String newValue){ requestRresponse = newValue; }
	
	public String answerAction(){
		tries++;
		if(problems.get(currentProblem).isCorrect(requestRresponse)) {
			score++;
			nextProblem();
			if(currentProblem == problems.size()) return "done.xhtml?faces-redirect=true";
			else return "success.xhtml?faces-redirect=true";
		}
		else if (tries == 1) return "again.xhtml?faces-redirect=true";
		else{
			nextProblem();
			if(currentProblem == problems.size()) return "done.xhtml?faces-redirect=true";
			else return "failure.xhtml?faces-redirect=true";
		}
	}
	
	private void nextProblem(){
		correctAnswer = problems.get(currentProblem).getAnswer();
		currentProblem++;
		tries = 0;
		requestRresponse = "";
	}
	
	public String startOverAction(){
		Collections.shuffle(problems);
		currentProblem = 0;
		score = 0;
		requestRresponse = "";
		return "index.xhtml?faces-redirect=true";
	}
	
	
	public int getCurrentProblem() {
		return currentProblem;
	}

	public void setCurrentProblem(int currentProblem) {
		this.currentProblem = currentProblem;
	}

	public String getSkipOutcome() {
		if (currentProblem < problems.size() - 1) return "index.xhtml?faces-redirect=true";
		else return "done.xhtml?faces-redirect=true";
	}
}
