package application.subsystem.Networking;

import application.subsystem.Utils.Utils;
import application.Controller;
import application.subsystem.Utils.BasicAlertBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectionBox {

	private String error = "Ip has to be in the format: xxx.xxx.xxx.xxx and the port must be greater than 1000 and less than 65535";

	public ConnectionBox(String title, int width, int height) {

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
		label.setText("Insert IP and Port of the server");

		TextField ip = new TextField();
		ip.setEditable(true);
		ip.setPromptText("IP:");

		TextField port = new TextField();
		port.setEditable(true);
		port.setPromptText("PORT:");

		Button button = new Button("Connect");
		button.setOnMouseClicked(e -> {
			// we check if ip and port are valid
			if (Utils.validIP(ip.getText()) && Utils.validPORT(port.getText())) {
				
				System.out.println("Connecting to: " + ip.getText() + ", Port: " + port.getText());
				
				Controller.client = new Client(ip.getText(),port.getText());

				window.close();
			} else {
				new BasicAlertBox("Error", error, 600, 150);
			}

		});

		VBox layout = new VBox();
		layout.getChildren().addAll(label, ip, port, button);
		layout.setAlignment(Pos.CENTER);
		VBox.setMargin(button, new Insets(20, 0, 0, 0));
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
