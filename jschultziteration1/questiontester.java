package mazeProject;

import java.sql.*;
public class QuestionTester {

	public static void main(String[] args) throws SQLException 
	{
		
		Question testQuest = new Question();
		Boolean result;
		System.out.print("Got question");
		result = testQuest.askQuestion();
		
		System.out.println(result);
		
		

	}

}
