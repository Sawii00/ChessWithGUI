package application.subsystem.Networking;

import application.Controller;
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

public class PortBox {
	private String error = "Port must be greater than 1000 and less than 65535";

	public PortBox(String title, int width, int height) {

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
		label.setText("Insert Port of the server");

		TextField port = new TextField();
		port.setEditable(true);
		port.setPromptText("PORT:");

		Button button = new Button("Set");
		button.setOnMouseClicked(e -> {
			// we check if ip and port are valid
			if (Utils.validPORT(port.getText())) {

				Controller.server = new Server(Integer.parseInt(port.getText()));

				window.close();
			} else {
				new BasicAlertBox("Error", error, 600, 150);
			}

		});

		VBox layout = new VBox();
		layout.getChildren().addAll(label, port, button);
		layout.setAlignment(Pos.CENTER);
		VBox.setMargin(button, new Insets(20, 0, 0, 0));
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
