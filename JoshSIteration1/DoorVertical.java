package mazeProject;

import java.sql.*;
/**
 *Created by isaac on 11/1/14.
 */
public class DoorVertical extends Door
{
    //we are going to us
    public Room _north;
    public Room _south;
    private Question _question;

    public DoorVertical(Room north, Room south)
    {
        super();//I think we need this
        _north = north;
        _south = south;

        north.setSouth(this);
        south.setNorth(this);

        _question = new Question();
    }

    public String toString()
    {
        String ret = "i am a vertical door\n";
    	 ret += "north room is: \n" + _north.toString() + "\nsouth room is: \n" + _south.toString();
    	return ret;
    }
    public Room getOtherRoom(Room r)
    {
        if(r == _north)
            return _south;

        return _north;//assuming room must be west in this case
    }

    //currently testing
    public boolean askQuestion() throws SQLException
    {
        System.out.println("True or false?");
        return _question.askQuestion();

    }
}
