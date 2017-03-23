package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
		}  								//Main class

	@Override
	public void start(final Stage primaryStage) {
		try {
			DataHandler.openLocalDB();
			Parent root = FXMLLoader.load(getClass().getResource("/ui/MainScreen.fxml"));	//full path to access other package resource
			primaryStage.setTitle("MotoRent");
			primaryStage.setScene(new Scene(root,800, 800));

			primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>()
			{
				@Override
				public void handle(WindowEvent window)
				{
	 				MainController.loadAllVehicles();
				}

			});
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
