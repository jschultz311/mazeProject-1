package mazeProject;

import java.sql.*;

public class Maze
{
    //members
    Room[][] _theMaze;
    Room _currentRoom;
    Room _winningRoom;//room the user needs to get to win
    //may not need these
    int _rows;
    int _cols;

    public Maze(int r, int c)
    {
	_rows = r;
	_cols = c;
	buildMaze();
	_currentRoom = _theMaze[0][0];//start player off at top right for now
	_winningRoom = _theMaze[_rows-1][_cols-1];//start with bottom right as winning room
    }

    private void buildMaze()
    {

	_theMaze = new Room[_rows][_cols];//init our maze member

	for(int r = 0; r < _rows; r++) {
	    for (int c = 0; c < _cols; c++) {
		_theMaze[r][c] = new Room(r,c);
	    }
	}

	Door temp;//


	//set up horizontal connections
	for(int r = 0; r < _rows; r++)
	    {
		for(int c = 0; c < _cols; c++)
		    {
			if(c < _cols - 1)
			    {
				temp = new DoorHorizontal(_theMaze[r][c], _theMaze[r][c+1]);
				// System.out.println(temp);
			    }

		    }
	    }

        //setting up vertical connections
	for(int c = 0; c < _cols; c++)
	    {
		for(int r = 0; r < _rows; r++)
		    {
			if(r < _rows - 1)
			    {
				temp = new DoorVertical(_theMaze[r][c], _theMaze[r+1][c]);
				// System.out.println(temp);
			    }
		    }
	    }
    }//end build _theMaze
	
    public Room getCurrentRoom(){return _currentRoom;}
    public boolean checkMove(String move) throws SQLException
    {
	if(_currentRoom.getDoor(move) == null)
	    return false;
	return true;
    }

    public boolean move(String move) throws SQLException
    {

	Door temp = _currentRoom.getDoor(move);

	boolean pass = temp.askQuestion();
	if(!pass)
		closeDoor(move);//we are going to close the door, if the player gets answer wrong
	if(pass)
		moveRooms(move);
	//we need to move to the new room here
	return checkWinStatus();
    }//end move method

    public void moveRooms(String move)
    {
	Door temp = _currentRoom.getDoor(move);
	_currentRoom = temp.getOtherRoom(_currentRoom);//will return the other Room
    }
    public boolean checkWinStatus()
    {
	if(_currentRoom == _winningRoom)
	    {
		System.out.println("You won!");
		return true;//game is over if you win
	    }
	
	//will check if we open doors in the current room
	//make more readable
	boolean res = _currentRoom.checkOpenDoors();
	if(res == false)
	    {
		System.out.println("No more open doors! You lose!");
		return true;
	    }

	return false;


    }
    public void closeDoor(String move)
    {
	_currentRoom.closeDoor(_currentRoom.getDoor(move));
    }

    //this method is for testing
    public void printCurrentDoors()
    {
	_currentRoom.checkAllDoors();
    }

    public void drawMaze()
    {
    	//we want to draw the maze and have some indicators for current room and rooms available
    	for(int r = 0; r < _rows; r++)
    	{
    		for(int c = 0; c < _cols; c++)
    		{
    			if(_theMaze[r][c] == _currentRoom)
    				System.out.print(" t ");//'t' is for token
    			else
    			System.out.print(_theMaze[r][c].draw());

    		}
    		System.out.println("");
    	}

    }

}//end class