package application.subsystem.IO;

import java.io.IOException;

import application.Controller;
import application.subsystem.Networking.Server;
import application.subsystem.Utils.BasicAlertBox;
import application.subsystem.Utils.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImportBox {
	

	public ImportBox(String title, int width, int height) {

		display(title, width, height);

	}

	private void display(String title, int width, int height) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(width);
		window.setMaxWidth(width);
		window.setMinHeight(height);
		window.setMaxHeight(height);
		window.setResizable(false);

		Label label = new Label();
		label.setText("Insert file path");

		TextField URI = new TextField();
		URI.setEditable(true);
		URI.setPromptText("URI:");

		Button button = new Button("Set");
		button.setOnMouseClicked(e -> {
			try {
				new FileReader(URI.getText());
				window.close();
			} catch (IOException e1) {
				new BasicAlertBox("Error","Error importing the save, check the path",250,100);
			}
			
			

		});

		VBox layout = new VBox();
		layout.getChildren().addAll(label, URI, button);
		layout.setAlignment(Pos.CENTER);
		VBox.setMargin(button, new Insets(20, 0, 0, 0));
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	

}
