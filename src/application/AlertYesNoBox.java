package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Confrirmation window with Title and Message. Shown before serious operations (ex Delete). Stores user's selection on MainController.AlertYesNoBoxChoise variable */
public class AlertYesNoBox {

    public static void display(String title, String message)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);

        Label lbl = new Label();
        lbl.setText(message);

        Button yesBtn = new Button("Ναι");
        yesBtn.setOnAction(event -> {
           MainController.AlertYesNoBoxChoise = "Ναι";
           window.close();
       });

        Button noBtn = new Button("Όχι");
        noBtn.setOnAction(event -> {
            MainController.AlertYesNoBoxChoise = "Όχι";
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lbl, yesBtn, noBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
