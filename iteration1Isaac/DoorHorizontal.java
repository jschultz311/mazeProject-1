

/**
 * Created by isaac on 11/1/14.
 */
public class DoorHorizontal extends Door
{
    Room _east;
    Room _west;
//    Question _question;

    public DoorHorizontal(Room west, Room east) {
        super();
        _west = west;
        _east = east;

        //assign Rooms this Door
        west.setEast(this);
        east.setWest(this);
//        _question = new Question();
    }
    public Room getEast(){return _east;}
    public Room getWest(){return _west;}
    public String toString()
    {
        String ret = "i am a horizontal door\n";
    	 ret += "west room is: \n" + _west.toString() + "\neast room is: \n" + _east.toString();
    	return ret;
    }
    public Room getOtherRoom(Room r)
    {
        if(r == _east)
            return _west;
        return _east;//assuming room must be west in this case
    }
    //currently testing
    public boolean askQuestion()
    {
        System.out.println("True or false?");
        return true;

    }



}//end class
