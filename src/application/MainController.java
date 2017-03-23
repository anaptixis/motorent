package application;


import com.sun.javafx.stage.StageHelper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    /** Collects data from UI, creates a Moto and calls DataHandler to store it in DB */
    public void insertNewMoto()
    {
        try {

            Stage currentStage = new Stage();

            ObservableList<Stage> stages = StageHelper.getStages() ;                            //get current stage to access textFields
            for(Stage stage : stages)
            {
                if(stage.isShowing())
                {
                    currentStage = stage;
                }
            }
            TextArea txtArea = (TextArea)(currentStage.getScene().lookup("#plate_input"));      //get plate
            String plate = txtArea.getText();


            txtArea = (TextArea)(currentStage.getScene().lookup("#rentalDays_input"));          //get rentalDays
            int totalRentalDays = Integer.parseInt(txtArea.getText());

            txtArea = (TextArea)(currentStage.getScene().lookup("#buyCost_input"));             //get buyCost
            double buyCost = Double.parseDouble(txtArea.getText());

            DataHandler.insertMoto(new Moto(plate, totalRentalDays, 0, buyCost, null));
            loadAllVehicles();                                                                  //refresh TableView

            txtArea = (TextArea)(currentStage.getScene().lookup("#plate_input"));               //clean TextAreas
            txtArea.setText("");
            txtArea = (TextArea)(currentStage.getScene().lookup("#rentalDays_input"));
            txtArea.setText("");
            txtArea = (TextArea)(currentStage.getScene().lookup("#buyCost_input"));
            txtArea.setText("");

        }catch (NumberFormatException e) {      //TODO: Handle this exception by showing something human readable to the user in case something goes wrong
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /** Overrides an already saved vehicle by altering values and calling DataHandler to update the vehicle in DB */
    public void editMoto()
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
        Vehicle vehicle = (Vehicle) grid.getSelectionModel().getSelectedItem();
        System.out.println("id: " + vehicle.getId());

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
        Moto moto = (Moto) grid.getSelectionModel().getSelectedItem();

        if (moto == null)                                                                       //check for empty Moto
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

        DataHandler.deleteMoto(moto);                                                           //call DataHandler to delete Moto from DB and refresh
        loadAllVehicles();

    }

}
