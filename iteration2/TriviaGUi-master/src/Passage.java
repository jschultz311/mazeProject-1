

/**
 * Created by Jeremy on 11/7/14.
 */
public class Passage implements PassageLeft, PassageRight, PassageUp, PassageDown
{
	private Room _room;
	private Question _question;

	/*
		A passage has a room and a question.
	*/
	public Passage(Room room) {
		_room = room;
		_question = new Question();
	}

	public Room getRoom() {
		return _room;
	}

	public Question getQuestion() {
		return _question;
	}

}
