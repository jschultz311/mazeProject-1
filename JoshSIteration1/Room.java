package mazeProject;


/**
 * Created by isaac on 11/1/14.
 */
public class Room
{
    //members
   public Door _north, _east, _south, _west;
   //temp vars
   int r, c;


    public Room(int row, int col)
    {
    	r = row;
    	c = col;
    }

   public void setSouth(Door d) { _south = d;}
   public void setNorth(Door d){_north = d;}
   public void setEast(Door d){_east = d;}
   public void setWest(Door d){_west = d;}

    public Door getSouth() { return _south;}
   public Door getNorth(){return _north;}
   public Door getEast(){return _east;}
   public Door getWest(){return _west;}

    public String toString()
    {
        return "Room:\n  row: " + r + " col: " + c;
    }

    public Door getDoor(String move)
    {
      if(move == null)
        return null;
      switch(move.toLowerCase())
      {
        case "up": 
          return getNorth();
          // break;
        case "down":
          return getSouth();
          // break;
        case "left":
          return getWest();
          // break;
        case "right":
          return getEast();
          // break;
        default:
          return null;
          // break;
      }//end switch statemnt

    }//end getDoor

public boolean checkOpenDoors()
{
  if(_west == null && _east == null && _north == null && _south == null)
    return false;//all doors are closed

  return true;//at least one open door
}

public void closeDoor(Door door)
{
  if(door == _west)
  {
    _west = null;
    System.out.println("Closed west door!");
  }
  else if(door == _east)
  {
    _east = null;
    System.out.println("Closed east door!");
  }
  else if(door == _north)
  {
    _north = null;
    System.out.println("Closed north door!");
  }
  else
  {
    _south = null;
    System.out.println("Closed south door!");
  }
}

public void checkAllDoors()
{
if(_west != null)
  System.out.println("west door is open");
  if(_east  !=  null)
    System.out.println("east door is open");
if(_north  != null)
  System.out.println("north door is open");
if(_south  !=  null)
  System.out.println("south door is open");

}

public String draw()
{
  return " R ";
}
 





}//end class
