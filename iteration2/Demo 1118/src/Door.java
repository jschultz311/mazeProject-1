package mazeProject;

import java.sql.*;


/**
 * Created by isaac on 11/1/14.
 */
public abstract class Door 
{
    //we may want to later include a draw method that is used later
    //in all of the door objects
    //public draw()
    //Question _question;
    public abstract String toString();
    public abstract boolean askQuestion() throws SQLException;
    public abstract Room getOtherRoom(Room r);

}
