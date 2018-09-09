package application;
	
import application.subsystem.Utils.AreYouSureAlertBox;
//import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*@TODO WHERE THERE'S STUFF TO DO
 * 
 * 
 * 
 * 
 * 
 * */


public class Main extends Application {
	
	public static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root  = FXMLLoader.load(getClass().getResource("Main.fxml"));
			mainScene = new Scene(root,720,720);
			primaryStage.setTitle("Chess");
			primaryStage.setScene(mainScene);
			primaryStage.setResizable(false);
			
			primaryStage.setOnCloseRequest(e->{
				e.consume();
				new AreYouSureAlertBox("Confirmation", "Are you sure you want to close the game?", 300, 200, ()->Platform.exit());
			});
			 
			//THIS IS SORT OF A GAMELOOP IN CASE WE NEED IT
			
//		    new AnimationTimer()
//		    {
//		        public void handle(long currentNanoTime)
//		        {
//		        	Controller.syncArrayGrid();
//		        }
//		    }.start();
			
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
