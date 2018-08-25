package application.subsystem.Utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	public AlertBox(String title, String message, int width,int height) {
		
		display(title,message,width,height);
		
	}
	
	
	private void display(String title, String message, int width, int height) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(width);
		window.setMaxWidth(width);
		window.setMinHeight(height);
		window.setMaxHeight(height);
		window.setResizable(false);
		
		Label label = new Label();
		label.setText(message);
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e->window.close());
		
		VBox layout = new VBox();
		layout.getChildren().addAll(label,closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		
		window.showAndWait();
	}

}
