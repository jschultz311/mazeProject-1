public class MazeViewer
{
	private Maze _maze;
	private Client _client;

	/*
		A mazeviewer needs a maze and a client to show current room
		and which moves are available.

		Simple viewer but shows MVC model.
	*/

	public MazeViewer(Maze maze, Client client){
		_maze = maze;
		_client = client;
	}

	public void showMoves() {
		System.out.print(_client.getName() + " can move to ");
		Room clientCurrentRoom = _client.getRoomCurrent();
		if(clientCurrentRoom.hasPassageUp())
			System.out.format("\n%02d(\"up\") ", clientCurrentRoom.getPassageUp().getRoom().getId());
		if(clientCurrentRoom.hasPassageLeft())
			System.out.format("\n%02d(\"left\") ", clientCurrentRoom.getPassageLeft().getRoom().getId());
		if(clientCurrentRoom.hasPassageRight())
			System.out.format("\n%02d(\"right\") ", clientCurrentRoom.getPassageRight().getRoom().getId());
		if(clientCurrentRoom.hasPassageDown())
			System.out.format("\n%02d(\"down\") ", clientCurrentRoom.getPassageDown().getRoom().getId());
		System.out.print("\n\n> ");
	}

	public void showMaze() {
		int rooms = _maze.getNumberRoom();
		int currentRoomId = _client.getRoomCurrent().getId();

		for(int roomId = 0 ; roomId < rooms*rooms; roomId++) {
			if(roomId%rooms == 0)
				System.out.println();
			if(roomId == currentRoomId)
				System.out.print("XX ");
			else
				System.out.format("%02d ", roomId);
		}
		System.out.println("\n");
	}

}