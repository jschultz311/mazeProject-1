package mazeProject;

import java.sql.*;
import java.util.Scanner;
/**
 * Created by isaac on 11/1/14.
 */

public class tester
{
    public static void main(String[] args) throws SQLException
    {
        //build maze
        Maze maze = new Maze(3,3);
        Scanner userIn = new Scanner(System.in);
        boolean done = false;
        while(!done)
        {

            maze.drawMaze();
            //tell user their door options
            maze.printCurrentDoors();   


            System.out.println("\nWhere would you like to move?");
            System.out.print("up, down, left, or right?  ");
            String move = userIn.nextLine();


            //verify if move is possible
            boolean canGo = maze.checkMove(move);
            System.out.println("canGo is " + canGo);

            if(canGo)
                done = maze.move(move);
            
        }


    }//end main

}
