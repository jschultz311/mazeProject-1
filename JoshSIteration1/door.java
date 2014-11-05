package mazeProject;

import java.sql.*;

public class Door 
{
	boolean answer;
	String userAnswer;
	int random;
	Question question;
	
	public Door()
	{
		answer = false;	
		question = new Question();
	}
	
	
	public boolean askQuestion() throws SQLException
	{
		if(random == 1)
		{
			userAnswer = question.getTFQuestion();
			answer = question.getTFAnswer(userAnswer);
			
		}
		else
		{
			userAnswer = question.getMultQuestion();
			answer = question.getMultAnswer(userAnswer);
		}
		return answer;
	}
	
	
}
