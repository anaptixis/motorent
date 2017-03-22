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

    /** Collects data from UI, creates a Moto and calls DataHandler to store it in DB */
    public void save_btn_clicked()
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

            txtArea = (TextArea)(currentStage.getScene().lookup("#buyCost_input"));              //get buyCost
            double buyCost = Double.parseDouble(txtArea.getText());

            DataHandler.saveMoto(new Moto(plate, totalRentalDays, 0,  buyCost, null ) );






        }catch (NumberFormatException e) {      //TODO: Handle this exception by showing something human readable to the user
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /** Overrides an already saved vehicle by altering values and calling DataHandler to update the vehicle in DB */
    public void edit_btn_clicked()
    {
        System.out.println("editing...");
    }

    /** Deletes a vehicle */
    public void delete_btn_clicked()
    {
        System.out.println("deleting...");
    }

}
