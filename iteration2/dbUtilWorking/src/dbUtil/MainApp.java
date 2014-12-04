package dbUtil;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dbUtil.Model.Data;
import dbUtil.view.dataOverviewController;
import dbUtil.view.editDataDialogController;

import java.io.IOException;
import java.sql.*;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    //for holding the list of Data
    private ObservableList<Data> listData = FXCollections.observableArrayList();

    //our database stuff
    private Connection con;
    private Statement stmt;
    //we want the list when we instatiate this class
    public MainApp(){

        //get the questions from the database
        getQuestions();
        //listData.add(new Data("Is the sky blue?", "T"));
    }

    private void getQuestions(){
        con = null;
        stmt = null;

        try{
            //register JDBC driver
            Class.forName("org.sqlite.JDBC");

            //open connection
            //TODO: change this when on a different machine
//            con = DriverManager.getConnection("jdbc:sqlite:/Users/thesilentp/Dropbox/Fall2014/SoftwareEngineering/dbUtilWorking/src/dbUtil/db/mazeQuestions.db");
            con = DriverManager.getConnection("jdbc:sqlite:/Users/isaac/Dropbox/Fall2014/SoftwareEngineering/MazeProject/mazeProject_working/mazeProject/iteration2/dbUtilWorking/src/dbUtil/db/mazeQuestions.db");
            System.out.println("Database opened successfully");

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM QUESTIONS");

            while(rs.next()){
                String question= rs.getString("question");
                String answer = rs.getString("answer");


                //add the data to the list
                listData.add(new Data(question,answer));
            }

            //close the connection when we're done.
            rs.close();
//            stmt.close();
//            con.close();




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(-1);
        }
    }

    private void insertIntoDb(){

        //erase the table, so we can add new data
        String deleteData = "DELETE FROM QUESTIONS";

        try{
            stmt.executeUpdate(deleteData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement ps = null;
        int i = 1;
        for(Data temp : listData){

            try{
                ps = con.prepareStatement("INSERT INTO QUESTIONS VALUES(?,?,?)");
                ps.setInt(1, i++ );
                ps.setString(2, temp.getQuestion());
                ps.setString(3, temp.getAnswer());
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //close new connections
        try{
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    private void closeConnections(){

        try{
            con.close();
            stmt.close();
            System.out.println("Database closed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList getListData(){
        return listData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Question Utility");
        initRootLayout();
        showDataOverview();
    }

    private void initRootLayout(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //show the scene containing the rootlayout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            //handle closing the application
            primaryStage.setOnCloseRequest( e -> {

                e.consume();//consume close request, so we can close properly
                //System.out.println("Now we can close the program properly");

                insertIntoDb();//put the information back into the database
                closeConnections();//close our connections to the db

                //close the stage
                primaryStage.close();

            });

        }
        catch(IOException e){
            e.printStackTrace();

        }

    }

    private void showDataOverview(){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/dataOverview.fxml"));

            AnchorPane dataOverview = (AnchorPane) loader.load();

            //put the anchorPane in the middle of the rootlayout

            rootLayout.setCenter(dataOverview);

            //set the contoller and give it access to this app
            dataOverviewController controller = loader.getController();
            controller.setMainApp(this);

        }
        catch(IOException e){
            e.printStackTrace();
            e.getMessage();
        }
    }
    //we are going to call this from the dataOverviewController
    public boolean showEditDialog(Data data){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/editDataDialog.fxml"));
            System.out.println("location: " + loader.getLocation());
            AnchorPane editDialog = (AnchorPane) loader.load();

            //create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Data");
            //set modality, so that the new stage is in side the previous stage area
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(editDialog);
            dialogStage.setScene(scene);

            //set the controller
            editDataDialogController controller = loader.getController();
            //we need to pass the control of the stage to the controller
            controller.setStageDialog(dialogStage);
            controller.setData(data);

            //show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    //to get the primaryStage
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
