package application;


import com.sun.javafx.stage.StageHelper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Date;

/**
 * Created by panos on 5/2/2017.
 *
 * Basic application functionality goes here.
 * Whenever the project starts, it connects the MainScreen.fxml file with this class
 * and the .fxml file looks here for the methods implementation.
 *
 */
public class MainController {

    public static String AlertYesNoBoxChoise;                                                   //stores user's selection for AlertBox('Ναι'/'Όχι')

    Vehicle prevClickedVehicle;
    Date lastClickTime;

    /** On MainScreen open, all Vehicles are loaded from DB and shown in TableView */
    public static void loadAllVehicles()
    {
        Stage currentStage = new Stage();
        ObservableList<Stage> stages = StageHelper.getStages() ;                                //get current Stage to access the TableView
        for(Stage stage : stages)
        {
            if(stage.isShowing())
            {
                currentStage = stage;
            }
        }
        TableView grid = (TableView)(currentStage.getScene().lookup("#vehicle_tableView"));     //get TableView from Stage

        int totalCols = 4;
        TableColumn<Vehicle, String> plateCol = new TableColumn<>("Αριθμός Πινακίδας");         //create and add 4 TableColumns on TableView
        plateCol.setMinWidth(100);
        plateCol.setPrefWidth((grid.getWidth() - 1) / totalCols);
        plateCol.setCellValueFactory(new PropertyValueFactory<>("plate"));

        TableColumn<Vehicle, String> totalRentDaysCol = new TableColumn<>("Αριθμός Ενοικιάσεων");
        totalRentDaysCol.setMinWidth(100);
        totalRentDaysCol.setPrefWidth((grid.getWidth()-1)/totalCols);
        totalRentDaysCol.setCellValueFactory(new PropertyValueFactory<>("totalRentDays"));

        TableColumn<Vehicle, String> totalGainCol = new TableColumn<>("Σύνολο");
        totalGainCol.setMinWidth(100);
        totalGainCol.setPrefWidth((grid.getWidth()-1)/totalCols);
        totalGainCol.setCellValueFactory(new PropertyValueFactory<>("buyCost"));    //TODO: buyCost here is for testing reasons, change it!

        TableColumn<Vehicle, String> finalGainCol = new TableColumn<>("Καθαρό κέρδος");
        finalGainCol.setMinWidth(100);
        finalGainCol.setPrefWidth((grid.getWidth()-1)/totalCols);
        finalGainCol.setCellValueFactory(new PropertyValueFactory<>("buyCost"));     //TODO: buyCost here is for testing reasons, change it!

        grid.setItems(DataHandler.getSavedVehicles());                                          //get data from DB
        grid.getColumns().addAll(plateCol, totalRentDaysCol, totalGainCol, finalGainCol);
    }

    /** Saves the current vehicle: If it exists, it updates it, else it inserts it */
    public void saveMoto()
    {
        Stage currentStage = new Stage();
        ObservableList<Stage> stages = StageHelper.getStages() ;                            //get current stage to access textFields
        for(Stage stage : stages)
        {
            if(stage.isShowing())
            {
                currentStage = stage;
            }
        }
        TextArea txtArea = (TextArea)(currentStage.getScene().lookup("#plate_input"));
        String plate = txtArea.getText();

        txtArea = (TextArea)(currentStage.getScene().lookup("#rentalDays_input"));      //get rentalDays
        int rentalDays = Integer.parseInt(txtArea.getText());

        txtArea = (TextArea)(currentStage.getScene().lookup("#buyCost_input"));         //get buyCost
        double buyCost = Double.parseDouble(txtArea.getText());

        if (DataHandler.vehicleExists(plate))
        {
            DataHandler.updateVehicle(new Moto(plate, rentalDays, 0, buyCost, null));
        }
        else
        {
            DataHandler.insertVehicle(new Moto(plate, rentalDays, 0, buyCost, null));
        }
        loadAllVehicles();
        clearTxtAreas();
    }

    /** Handles a double click event and populates the fields with data from the clicked row*/
    public void populateFields()
    {

        Stage currentStage = new Stage();
        ObservableList<Stage> stages = StageHelper.getStages() ;                                //get current Stage to access the TableView
        for(Stage stage : stages)
        {
            if(stage.isShowing())
            {
                currentStage = stage;
            }
        }
        TableView grid = (TableView)(currentStage.getScene().lookup("#vehicle_tableView"));     //get TableView from Stage
        Vehicle curClickedVehicle = (Vehicle) grid.getSelectionModel().getSelectedItem();

        if (curClickedVehicle==null) return;                                                    //clicked same row twice within 300 millis
        if(curClickedVehicle!=prevClickedVehicle){
            prevClickedVehicle=curClickedVehicle;
            lastClickTime=new Date();
        } else if(curClickedVehicle==prevClickedVehicle) {
            long diff = new Date().getTime() - lastClickTime.getTime();
            if (diff < 300){

                TextArea txtArea = (TextArea)(currentStage.getScene().lookup("#plate_input"));  //populate plate
                txtArea.setText(curClickedVehicle.getPlate());

                txtArea = (TextArea)(currentStage.getScene().lookup("#rentalDays_input"));      //get rentalDays
                txtArea.setText(curClickedVehicle.getTotalRentDays() + "");

                txtArea = (TextArea)(currentStage.getScene().lookup("#buyCost_input"));         //get buyCost
                txtArea.setText(curClickedVehicle.getBuyCost() + "");

            } else {
                lastClickTime = new Date();
            }
        }
    }

    /** Clear text areas */
    public void clearTxtAreas()
    {
        Stage currentStage = new Stage();
        ObservableList<Stage> stages = StageHelper.getStages() ;                            //get current stage to access textFields
        for(Stage stage : stages)
        {
            if(stage.isShowing())
            {
                currentStage = stage;
            }
        }
        TextArea txtArea = (TextArea)(currentStage.getScene().lookup("#plate_input"));      //get and clean
        txtArea.setText("");
        txtArea = (TextArea)(currentStage.getScene().lookup("#rentalDays_input"));
        txtArea.setText("");
        txtArea = (TextArea)(currentStage.getScene().lookup("#buyCost_input"));
        txtArea.setText("");
    }

    /** Deletes the selected vehicle */
    public void deleteMoto()
    {

        Stage currentStage = new Stage();
        ObservableList<Stage> stages = StageHelper.getStages() ;                                //get current Stage to access the TableView
        for(Stage stage : stages)
        {
            if(stage.isShowing())
            {
                currentStage = stage;
            }
        }
        TableView grid = (TableView)(currentStage.getScene().lookup("#vehicle_tableView"));    //get TableView from Stage
        Vehicle v = (Vehicle) grid.getSelectionModel().getSelectedItem();

        if (v == null)                                                                       //check for empty Moto
        {
            AlertOKBox.display("Προσοχή", "Δεν έχετε επιλέξει μηχανάκι προς διαγραφή!");
            return;
        }

        AlertYesNoBoxChoise = "Όχι";                                                            //show confirmation message before deleting
        AlertYesNoBox.display("Προσοχή!", "Πρόκειται να διαγράψετε το επιλεγμένο μηχανάκι. Είστε σίγουροι ότι θέλετε να συνεχίσετε;");
        if (AlertYesNoBoxChoise == "Όχι")
        {
            return;
        }

        DataHandler.deleteVehicle(v);                                                           //call DataHandler to delete Moto from DB and refresh
        loadAllVehicles();

    }

}
