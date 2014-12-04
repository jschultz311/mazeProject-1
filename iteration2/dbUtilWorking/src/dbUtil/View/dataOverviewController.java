package dbUtil.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;
import dbUtil.MainApp;
import dbUtil.Model.Data;

public class dataOverviewController {

    //tableview and its columns
    @FXML
    private TableView<Data> tableData;
//    @FXML
//    private TableColumn<Data, Integer> columnId;
    @FXML
    private TableColumn<Data, String> columnQuestion;
    @FXML
    private TableColumn<Data, String> columnAnswer;
    @FXML
    private Button buttonNew;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;

    private MainApp mainApp;



    //generic constructor for using the controller in the mainApp
    //the constructor must be declared as public to be used!!
    public dataOverviewController(){
    }

    @FXML
    protected void initialize(){
        //we want to put all the tabledata inside of the table
        //columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        columnQuestion.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
        columnAnswer.setCellValueFactory(cellData -> cellData.getValue().answerProperty());
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        //add observable list data to the table
        tableData.setItems(mainApp.getListData());
    }

    //TODO: have the buttons do something when you click on them

    @FXML
    protected void handleDeleteData(){
       int selectedindex = tableData.getSelectionModel().getSelectedIndex();
        if(selectedindex >= 0) {
            //remove the person from the table
            tableData.getItems().remove(selectedindex);
        }
        //this means that the table is empty
        else{
            Dialogs.create()
                    .title("No selection")
                    .masthead("No Data Selected!")
                    .message("Please select some data in the table")
                    .showWarning();
        }

    }

    @FXML
    protected void handleNewData(){
        Data tempData = new Data();
        if(tempData!= null){
            boolean okClicked = mainApp.showEditDialog(tempData);
            if(okClicked){
                mainApp.getListData().add(tempData);
            }
        }

    }
    @FXML
    protected void handleEditData(){

        //get the selected data from the table
        Data selectedData = tableData.getSelectionModel().getSelectedItem();
        if(selectedData != null){
            //if okClicked true, then we should add the edited, else do nothing
            boolean okClicked = mainApp.showEditDialog(selectedData);

        }
        else{
            //nothing select
            Dialogs.create()
                    .masthead("No selection")
                    .title("No data selected")
                    .message("Please select some data in the table")
                    .showWarning();
        }

    }

}
