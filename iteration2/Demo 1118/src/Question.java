package mazeProject;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Question 
{
	Random rand = new Random();
	int random = 0;
	int range;
	String quest;
	String userAnswer;
	boolean answer;
	Connection c = null;
	ResultSet rs;
	ResultSet countRows;
	Statement stmt;
	Scanner kb = new Scanner(System.in);
	
	public Question()
	{
		quest = null;
		answer = false;
		connectDb();
	}
	
	private void connectDb()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:mazeQuestions.db");
			
		}
		catch ( Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			
		}
		
	}
	
	
	public String getQuestion() throws SQLException 
	{
		stmt = c.createStatement();
		countRows = stmt.executeQuery("SELECT * FROM QUESTIONS");
		range = databaseRows(countRows);
		rs = stmt.executeQuery( "SELECT question FROM QUESTIONS WHERE id == " + (range)); 
		quest = rs.getString("question");
		System.out.println(quest);
		userAnswer = kb.next();
		return userAnswer;
	}

	public boolean getAnswer(String guess) throws SQLException
	{
		String correct;
		stmt = c.createStatement();
		rs = stmt.executeQuery( "SELECT answer FROM QUESTIONS WHERE question like '%" + quest + "%'");
		correct = rs.getString("answer");
		if(guess.equals(correct))
		{
			answer = true;
			System.out.println("Correct!");
		}
		else
		{
			answer = false;
			System.out.println("Wrong!");
		}
		
		return answer;
	}

	public boolean askQuestion() throws SQLException
	{
		userAnswer = getQuestion().toUpperCase();
		answer = getAnswer(userAnswer);
		
		return answer;
	}
	
	
	public int databaseRows(ResultSet newSet) throws SQLException
	{
		int count = 0;
		do
		{
			count ++;
		}while(newSet.next());
		
		random = rand.nextInt(count-2) + 1;
		return random;
	}
	
}
