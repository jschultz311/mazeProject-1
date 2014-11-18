import java.util.Scanner;

import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.TerminalInterface;

public class Test
{
    public static void main(String[] args)
    {
    	
        Scanner sc = new Scanner(System.in);
        String moveString = "";
        int ch = BlackenKeys.NO_KEY;

        System.out.print("How many rooms(entering 4 means 16, 5 means 25, ...): ");
        Maze maze = new Maze(10);
//        sc.nextLine(); // clear line
        System.out.print("What is your name?: ");
        Client client = new Client("jeremy", maze.getRoomStart());
        MazeViewer mazeViewer = new MazeViewer(maze, client);
        
        MazeViewerGUI mazeViewerGUI = new MazeViewerGUI(maze, client);
        TerminalInterface term = mazeViewerGUI.getTerminal();


        while(!moveString.equals("quit") && !client.getRoomCurrent().equals(maze.getRoomEnd())) {
            mazeViewer.showMaze();
            mazeViewer.showMoves();
            mazeViewerGUI.showMap();
            ch = term.getch();
            client.move(ch);
        }
        term.quit();
        sc.close();


    }

}