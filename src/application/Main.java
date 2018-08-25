package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root  = FXMLLoader.load(getClass().getResource("Main.fxml"));
			mainScene = new Scene(root,720,720);
			primaryStage.setTitle("Chess");
			primaryStage.setScene(mainScene);
			primaryStage.show();
			
			
			Controller.initialize();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
