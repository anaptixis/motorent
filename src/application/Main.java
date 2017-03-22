package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
		}  								//Main class

	@Override
	public void start(Stage primaryStage) {
		try {
			DataHandler.openLocalDB();
			Parent root = FXMLLoader.load(getClass().getResource("/ui/MainScreen.fxml"));	//full path to access other package resource
			primaryStage.setTitle("MotoRent");
			primaryStage.setScene(new Scene(root,800, 800));
			primaryStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
