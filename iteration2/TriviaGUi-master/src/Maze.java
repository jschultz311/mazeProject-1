import java.util.HashMap;

/**
 * Created by Jeremy on 11/7/14.
 */
public class Maze
{
	private int _rooms;
	private HashMap<Integer, Room> roomsMap;
	private int roomEndId;

	/*
		A maze knows its starting and ending rooms. It also
		knows that it has rooms. The maze does not care how
		the rooms are connected. It will give the client any
		room that the client asks for.
	*/
	public Maze(int rooms) {
		_rooms = rooms;
		roomsMap = new HashMap<Integer, Room>(rooms*rooms);
		roomEndId = rooms * rooms - 1;
		createMaze();
	}

	/*
		Creates rooms in the maze. Time cost is the number of rooms set.
		If a room has a neighbor to the left or above itself then it will
		set passages to both.
	*/
	private void createMaze() {
		for(int roomId = 0 ; roomId < _rooms*_rooms; roomId++) {
			Room roomCurrent = new Room(roomId);
			roomsMap.put(roomId, roomCurrent);

			if(hasRoomAdjacentLeft(roomId)){
				Room roomLeft = roomsMap.get(roomId - 1);
				addPassagesLR(roomCurrent, roomLeft);
			}
			if(hasRoomAdjacentUp(roomId)){
				Room roomUp = roomsMap.get(roomId - _rooms);
				addPassagesUD(roomCurrent, roomUp);
			}
		}
	}

	// return the starting room in the maze.
	public Room getRoomStart() {
		return roomsMap.get(0);
	}
	// return the ending room in the maze.
	public Room getRoomEnd() {
		return roomsMap.get(roomEndId);
	}
	// check if the maze has a room with id(int)
	public boolean hasRoom(int roomId) {
		return roomsMap.containsKey(roomId);
	}
	// get room from the maze with id(int)
	public Room getRoom(int roomId) {
		if(roomsMap.containsKey(roomId))
			return roomsMap.get(roomId);
		throw new RuntimeException("No room");
	}
	// check if the current room has a neighbor to its left.
	private boolean hasRoomAdjacentLeft(int roomId) {
		return (roomsMap.containsKey(roomId - 1) && roomId%_rooms != 0);
	}
	// check if the current room has a neighbor above itself.
	private boolean hasRoomAdjacentUp(int roomId) {
		return roomsMap.containsKey(roomId - _rooms);
	}
	// add passages to the left neighbor and to itself.
	private void addPassagesLR(Room roomCurrent, Room roomAdjacent) {
		PassageLeft passageToAdjecent = new Passage(roomAdjacent);
		PassageRight passageToCurrent = new Passage(roomCurrent);
		roomCurrent.addPassage(passageToAdjecent);
		roomAdjacent.addPassage(passageToCurrent);
	}
	// add passages to the top neighbor and to itself.
	private void addPassagesUD(Room roomCurrent, Room roomAdjacent) {
		PassageUp passageToAdjecent = new Passage(roomAdjacent);
		PassageDown passageToCurrent = new Passage(roomCurrent);
		roomCurrent.addPassage(passageToAdjecent);
		roomAdjacent.addPassage(passageToCurrent);
	}
	// return the number of rooms created. if there are 16 rooms this will return 4. 25 will return 5. etc.
	public int getNumberRoom() {
		return _rooms;
	}

}
