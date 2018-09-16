package application;

import application.subsystem.Utils.AreYouSureAlertBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*@TODO WHERE THERE'S STUFF TO DO
 * 
 * PROBLEM:
 * 	WHEN YOU MOVE A PIECE AFTER HITTING HOST BUT BEFORE HAVING A CLIENT CONNECTED, IT DOES NOT MOVE
 * WHEN A CLIENT CONNECTS AND YOU MOVE A DIFFERENT PIECE, THE PREVIOUS PIECE MOVES WHERE YOU TOLD HIM TO
 * #NEVERLUCKY
 * 
 * Sync the table when reconnecting
 * Server needs to send a message stating the turn
 * Create primary file for board status
 * 
 * 
 * 
 * Bug: when hitting host or connect and then closing the pop window
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
				new AreYouSureAlertBox("Confirmation", "Are you sure you want to close the game?", 300, 200, ()->{
					if (Controller.server!=null) {
						Controller.server.socketClose();
						
						System.out.println("Socket chiuso");

					}
					else if (Controller.client!=null) {
						Controller.client.socketClose();
						System.out.println("Socket chiuso");
						
					}

					Platform.exit();
					System.exit(0);
					});
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
