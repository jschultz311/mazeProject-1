package dbUtil.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by isaac on 12/2/14.
 */
public class Data {

    private final IntegerProperty id;
    private final StringProperty question;
    private final StringProperty answer;

    public Data(){
        this(null, null);
    }

    public Data(String question, String answer){
        this.id = new SimpleIntegerProperty(-1);
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getQuestion() {
        return question.get();
    }

    public StringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswer() {
        return answer.get();
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }
}
