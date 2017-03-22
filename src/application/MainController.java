package application;


import com.sun.javafx.stage.StageHelper;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
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

    /** Create a vehicle */
    public void save_btn_clicked()
    {
        try {
            int nextId = DataHandler.getConnection().createStatement().executeQuery("SELECT MAX(id) FROM Vehicle").getInt(1) + 1 ;  //id
            Stage currentStage = new Stage();

            ObservableList<Stage> stages = StageHelper.getStages() ;                                                                //get current stage to access textFields
            for(Stage stage : stages)
            {
                if(stage.isShowing())
                {
                    currentStage = stage;
                }
            }
            TextArea txtArea = (TextArea)(currentStage.getScene().lookup("#plate_input"));                                          //get plate
            String plate = txtArea.getText();

            txtArea = (TextArea)(currentStage.getScene().lookup("#rentalDays_input"));                                              //get rentalDays
            int totalRentalDays = Integer.parseInt(txtArea.getText());

            txtArea = (TextArea)(currentStage.getScene().lookup("#buyCost_input"));                                                 //get buyCost
            double buyCost = Double.parseDouble(txtArea.getText());

            DataHandler.getConnection().createStatement().execute(                                                                  //insert query
                    "INSERT INTO Vehicle (id, plate, totalRentalDays, buyCost) VALUES ("+nextId+",'"+plate+"', "+totalRentalDays+", "+buyCost+") ;");

            System.out.println("Saved successfully! " +nextId+":"+plate+"   "+totalRentalDays+"   "+buyCost);


        }catch (NumberFormatException e) {      //TODO: Handle this exception by showing something human readable to the user
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /** Override an already saved vehicle */
    public void edit_btn_clicked()
    {
        System.out.println("editing...");
    }

    /** Delete a vehicle */
    public void delete_btn_clicked()
    {
        System.out.println("deleting...");
    }

}
