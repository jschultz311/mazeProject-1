package mazeProject;

import java.sql.*;

public class SQLiteJDBC 
{
	public static void main( String args[]) throws SQLException
	{
		
		//create database
		Connection c = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			
		}
		catch ( Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		//create table
		Statement stmt = c.createStatement();
		String sql;
		try
		{
			
			 sql = "CREATE TABLE COMPANY" +
						 "(ID INT 	NOT NULL," +
						 "NAME			  TEXT	NOT NULL, " +
						 "AGE			  INT	NOT NULL, " +
						 "ADDRESS		  CHAR(50), " +
						 "SALARY		  INT	NOT NULL)";
			
			stmt.executeUpdate(sql);
			stmt.close();
		}
		catch (Exception e)
		{
			System.out.println("Table created");
		}
		
		//add values to table
		stmt = c.createStatement();
		sql = "INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) " +
					 "VALUES (1, 'Paul', 32, 'California', 20000 );";
		stmt.executeUpdate(sql);
		
		sql = "INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) " +
				 "VALUES (2, 'Allen', 25, 'Texas', 15000 );";
		stmt.executeUpdate(sql);
		
		
		stmt.close();
		c.commit();
		c.close();
		
	}
}
