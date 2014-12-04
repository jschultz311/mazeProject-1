package dbUtil.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import dbUtil.Model.Data;

/**
 * Created by isaac on 12/3/14.
 */
public class editDataDialogController {

    @FXML
    private TextField fieldQuestion;
    @FXML
    private TextField fieldAnswer;
    @FXML
    private Button buttonOK;
    @FXML
    private Button buttonCancel;

    //we need to keep track of the different stages
    private Stage stageDialog;
    private Data data; //this is the data that we are going to edit in the dialog
    private boolean okClicked = false;

    public editDataDialogController(){

    }
    @FXML
    protected void initialize(){

    }
    //set our stage
    public void setStageDialog(Stage stageDialog){
        this.stageDialog = stageDialog;
    }

    //set the data to be edited
    //this will allow the data to appear when we open the dialog
    public void setData(Data data){
        this.data = data;
        //set the data in the fields to reflect the current information
        fieldQuestion.setText(data.getQuestion());
        fieldAnswer.setText(data.getAnswer());

    }

    public boolean isOkClicked(){return okClicked;}

    @FXML
    protected void handleCancel(){
        //do nothing if we press the cancel button.
        //just close the stage
        stageDialog.close();
    }

    //this should change the information held inside of the data object after the data has been validated
    @FXML
    protected void handleOk(){

        //we need to test the input first!
        if(inputValid()){
            //we need to change the data in this case
            data.setQuestion(fieldQuestion.getText());
            data.setAnswer(fieldAnswer.getText());

            okClicked = true;
            stageDialog.close();
        }
            //we throw an error when we are checking the input
    }

    private boolean inputValid(){

        String errorMessage = "";
        //we should not have any empty fields or any null values
        if(fieldQuestion.getText() == null || fieldQuestion.getText().length() == 0)
            errorMessage += "No valid question!\n";
        if(fieldAnswer.getText() == null || fieldAnswer.getText().length() == 0)
            errorMessage += "No valid answer";

        //we have valid input in this case
        if(errorMessage.length() == 0)
            return true;
        //create an error message in this case
        //return false
        Dialogs.create()
                .title("Invalid Fields")
                .masthead("Please correct the invalid fields")
                .message(errorMessage)
                .showError();
        return false;

    }




}
