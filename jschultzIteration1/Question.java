package mazeProject;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Question 
{
	Random rand = new Random();
	int random = 0;
	int range = 1;
	String quest;
	String userAnswer;
	boolean answer;
	Connection c = null;
	ResultSet rs;
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
		System.out.println("Opened database successfully");
	}
	
	public String getTFQuestion() throws SQLException 
	{
		stmt = c.createStatement();
		//rs = stmt.executeQuery("SELECT * FROM TRUEFALSE");
		//range = databaseRows(rs);
		rs = stmt.executeQuery( "SELECT question FROM TRUEFALSE WHERE id == " + range); 
		quest = rs.getString("question");
		System.out.println(quest);
		userAnswer = kb.next();
		return userAnswer;
	}
	
	public String getMultQuestion() throws SQLException
	{
		stmt = c.createStatement();
		//rs = stmt.executeQuery("SELECT * FROM MULTIPLE");
		//range = databaseRows(rs);
		rs = stmt.executeQuery( "SELECT question FROM MULTIPLE WHERE id == " + range); 
		quest = rs.getString("question");
		System.out.println(quest);
		userAnswer = kb.next();
		return userAnswer;
	}
	
	public boolean getTFAnswer(String guess) throws SQLException
	{
		String correct;
		stmt = c.createStatement();
		rs = stmt.executeQuery( "SELECT answer FROM TRUEFALSE WHERE question like '%" + quest + "%'");
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
	
	public boolean getMultAnswer(String guess) throws SQLException
	{
		String correct;
		stmt = c.createStatement();
		rs = stmt.executeQuery( "SELECT answer FROM MULTIPLE WHERE question like '%" + quest + "%'");
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
		random = rand.nextInt(2);
		System.out.println(random);
		if(random != 1)
		{
			userAnswer = getTFQuestion();
			answer = getTFAnswer(userAnswer);
			
		}
		else
		{
			userAnswer = getMultQuestion();
			answer = getMultAnswer(userAnswer);
		}
		return answer;
	}
	
	
	public int databaseRows(ResultSet newSet) throws SQLException
	{
		int count = 0;
		do
		{
			count ++;
		}while(newSet.next());
		
		return count;
	}
	
}
