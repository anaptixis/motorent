package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Alerter window that shows a message. Needs a Title and a Message*/
public class AlertOKBox {

    public static void display(String title, String message)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);

        Label lbl = new Label();
        lbl.setText(message);

        Button okBtn = new Button("Ναι");
        okBtn.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lbl, okBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
