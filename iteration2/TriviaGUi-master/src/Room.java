/**
 * Created by Jeremy on 11/7/14.
 */
public class Room
{
	private int _id;
	private boolean _hasPassageLeft;
	private boolean _hasPassageRight;
	private boolean _hasPassageUp;
	private boolean _hasPassageDown;
	private PassageLeft _passageLeft;
	private PassageRight _passageRight;
	private PassageUp _passageUp;
	private PassageDown _passageDown;

	/*
		A room can hold four passages. The room will know
		which passages it holds. A room also has an id starting from 0.
		Thus if there are 4 rooms then the ids will be 0, 1 , 2, 3.
		The ids are used for connecting rooms to eachother.
	*/

	public Room(int id) {
		_id = id;
		_hasPassageLeft = false;
		_hasPassageRight = false;
		_hasPassageUp = false;
		_hasPassageDown = false;
	}

	public int getId() {
		return _id;
	}

	// Uses method overloading with interfaces. I believe this will make GUI stuff easier.
	public void addPassage(PassageLeft passage) {
		_passageLeft = passage;
		_hasPassageLeft = true;
	}

	public void addPassage(PassageRight passage) {
		_passageRight = passage;
		_hasPassageRight = true;
	}

	public void addPassage(PassageUp passage) {
		_passageUp = passage;
		_hasPassageUp = true;
	}

	public void addPassage(PassageDown passage) {
		_passageDown = passage;
		_hasPassageDown = true;
	}

	public boolean hasPassageLeft() {
		return _hasPassageLeft;
	}

	public boolean hasPassageRight() {
		return _hasPassageRight;
	}

	public boolean hasPassageUp() {
		return _hasPassageUp;
	}

	public boolean hasPassageDown() {
		return _hasPassageDown;
	}

	public PassageLeft getPassageLeft() {
		return _passageLeft;
	}

	public PassageRight getPassageRight() {
		return _passageRight;
	}

	public PassageUp getPassageUp() {
		return _passageUp;
	}

	public PassageDown getPassageDown() {
		return _passageDown;
	}
}
