public class Question
{

	public Question(){

	}

	public String getQuestion() {
		return "Am I Awesome?";
	}

	public String getAnswer() {
		return "Yes";
	}

	@Override
	public String toString() {
		return getQuestion();
	}

}