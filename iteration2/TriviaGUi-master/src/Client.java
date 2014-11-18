import com.googlecode.blacken.terminal.BlackenKeys;



/**
 * Created by Jeremy on 11/7/14.
 */
public class Client
{

	private String _name;
	private Room _currentRoom;
	private int _currentRoomId;
	private boolean _quit;

	/*
		A client has a name and has a room which it resides in.
		The client can ask to move [up, left, down, right] and the room
		will grant the request if it has a passage to that direction.
	*/
	public Client(String name, Room currentRoom) {
		_quit = false;
		_name = name;
		_currentRoom = currentRoom;
		_currentRoomId = _currentRoom.getId();
	}

	public void setRoom(Room newRoom) {
		_currentRoom = newRoom;
	}

	public Room getRoomCurrent() {
		return _currentRoom;
	}
	
	public int getRoomCurrentId() {
		return _currentRoomId;
	}

	public String getName() {
		return _name;
	}
	
	public boolean iQuit() {
		return _quit;
	}
	
	public boolean move(int ch) {
        switch (ch) {
        case 'j':
        case BlackenKeys.KEY_DOWN:
        case 's':
        case BlackenKeys.KEY_KP_DOWN:
        	moveDown();
            break;
        case 'k':
        case BlackenKeys.KEY_UP:
        case 'w':
        case BlackenKeys.KEY_KP_UP:
        	moveUp();
            break;
        case 'h':
        case BlackenKeys.KEY_LEFT:
        case 'a':
        case BlackenKeys.KEY_KP_LEFT:
        	moveLeft();
            break;
        case 'l':
        case BlackenKeys.KEY_RIGHT:
        case 'd':
        case BlackenKeys.KEY_KP_RIGHT:
        	moveRight();
            break;
        case 'q':
        case 'Q':
        case BlackenKeys.KEY_ESCAPE:
            _quit = true;
            return false;
        default:
            return false;
        }
        return true;
    }

//	public void move(String moveDirection) {
//		if (moveDirection.equals("left"))
//			moveLeft();
//		else if (moveDirection.equals("right"))
//			moveRight();
//		else if (moveDirection.equals("up"))
//			moveUp();
//		else if (moveDirection.equals("down"))
//			moveDown();
//		else if (moveDirection.equals("quit")){}
//		else {
//			System.out.println("Move not valid");
//		}
//	}

	public void moveLeft() {
		if(_currentRoom.hasPassageLeft()) {
			// answerQuestion((Passage)_currentRoom.getPassageLeft());
			_currentRoom = _currentRoom.getPassageLeft().getRoom();
			_currentRoomId = _currentRoom.getId();
		}
	}

	public void moveRight() {
		if(_currentRoom.hasPassageRight()) {
			// answerQuestion((Passage)_currentRoom.getPassageRight());
			_currentRoom = _currentRoom.getPassageRight().getRoom();
			_currentRoomId = _currentRoom.getId();
		}
	}

	public void moveUp() {
		if(_currentRoom.hasPassageUp()) {
			// answerQuestion((Passage)_currentRoom.getPassageUp());
			_currentRoom = _currentRoom.getPassageUp().getRoom();
			_currentRoomId = _currentRoom.getId();
		}
	}

	public void moveDown() {
		if(_currentRoom.hasPassageDown()) {
			// answerQuestion((Passage)_currentRoom.getPassageDown());
			_currentRoom = _currentRoom.getPassageDown().getRoom();
			_currentRoomId = _currentRoom.getId();
		}
	}

	public boolean answerQuestion(Passage passage) {
		System.out.println(passage.getQuestion());
		return true;
	}

}
