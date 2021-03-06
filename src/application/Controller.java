package application;

import application.subsystem.Game.Board;
import application.subsystem.Game.PlayerManager;
import application.subsystem.IO.File;
import application.subsystem.IO.ImportBox;
import application.subsystem.Networking.Client;
import application.subsystem.Networking.ConnectionBox;
import application.subsystem.Networking.PortBox;
import application.subsystem.Networking.Server;
import application.subsystem.Pieces.BasicPiece;
import application.subsystem.Utils.AreYouSureAlertBox;
import application.subsystem.Utils.BasicAlertBox;
import javafx.application.Platform;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Controller {

	public static Board board;
	static GridPane gridPane;
	static Label tempLabel;
	public static Server server;
	public static Client client;
	public static PlayerManager pm;

	public static boolean isHosting = false;
	public static boolean isConnected = false;

	public Controller() {

	}

	public static void initialize() {
		pm = new PlayerManager();
		board = new Board(8, 8);
		gridPane = (GridPane) Main.mainScene.lookup("#gridPane");
		syncArrayGrid();
		setMenus();

	}

	public void dragDetected(MouseEvent e) {

		Dragboard db = ((Label) e.getSource()).startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		int rowID = GridPane.getRowIndex((Label) e.getSource());
		int colID = GridPane.getColumnIndex((Label) e.getSource());
		content.putString("" + rowID + "," + colID);

		db.setContent(content);

		e.consume();

	}

	public void dragOver(DragEvent e) {
		e.acceptTransferModes(TransferMode.ANY);
		e.consume();
	}

	public void dragDropped(DragEvent e) {
		Dragboard db = e.getDragboard();
		int sourceRowID = Integer.parseInt(db.getString().split(",")[0]);
		int sourceColID = Integer.parseInt(db.getString().split(",")[1]);

		// System.out.println(sourceColID+" "+sourceRowID);

		int targetRowID = GridPane.getRowIndex((Label) e.getSource());
		int targetColID = GridPane.getColumnIndex((Label) e.getSource());
		// CORRECTLY RETRIEVED THE R0W AND COL ID OF THE GRID LETS GOOOOOOOOOOO
		// System.out.println(targetColID+" "+targetRowID);

		// retrieve the moving piece
		BasicPiece moving = Board.squares[sourceColID][sourceRowID].piece;
		// moving.move(Board.squares[targetColID][targetRowID]);

		moving.player.move(moving, Board.squares[targetColID][targetRowID]);

		e.setDropCompleted(true);
		e.consume();
		syncArrayGrid();

	}

	public static void syncArrayGrid() {

		for (int i = 0; i < Board.squares.length; i++) {
			for (int j = 0; j < Board.squares[i].length; j++) {
				tempLabel = (Label) gridPane.lookup("#pos" + i + "" + j + "");

				try {
					tempLabel.setText(Board.squares[i][j].piece.getType().toString());
					if(Board.squares[i][j].piece.player.name == "Player 2")
					{
						tempLabel.setTextFill(Color.GRAY);
					}
					else if(Board.squares[i][j].piece.player.name == "Player 1")
					{
						tempLabel.setTextFill(Color.BLACK);				
					}
				} catch (Exception e) {
					tempLabel.setText("");
				}

				// FOR DEBUGGING PURPOSES
				// try {
				// System.out.print(Board.squares[i][j].piece.getType().toString()+" ");
				// } catch (Exception e) {
				// System.out.print("null ");
				// }

			}
			// System.out.println("");
		}
	}

	private static void setMenus() {
		MenuBar menu = (MenuBar) Main.mainScene.lookup("#menuBar");
		// @TODO: IF WE CHANGE NUMBER OF MENUS WE GOTTA CHANGE THE ID DOWN BELOW
		Menu game = menu.getMenus().get(0);
		Menu multiplayer = menu.getMenus().get(1);
		Menu help = menu.getMenus().get(2);

		// @TODO we have to set the reset button
		MenuItem save = game.getItems().get(0);
		MenuItem importGame = game.getItems().get(1);
		MenuItem reset = game.getItems().get(2);
		MenuItem close = game.getItems().get(3);
		CheckMenuItem host = (CheckMenuItem) multiplayer.getItems().get(0);
		CheckMenuItem connect = (CheckMenuItem) multiplayer.getItems().get(1);
		MenuItem about = help.getItems().get(0);
		
		save.setOnAction(e->{
			
			File file = new File("Save",Board.boardToString());
			file.writeToDisk("");
			
		});

		importGame.setOnAction(e -> {

			new ImportBox("Import", 250, 250);

		});

		reset.setOnAction(e -> {
			if (host.isSelected() || connect.isSelected()) {
				new BasicAlertBox("Error", "Cannot reset a multiplayer game", 250, 100);
			} else {
				initialize();
			}
		});

		close.setOnAction(e -> {
			// YOU MIGHT WANNA DO SOME SAVING BEFORE QUITTING :)
			new AreYouSureAlertBox("Confirmation", "Are you sure you want to close the game?", 300, 200, () -> {
				if (server != null) {
					server.socketClose();

					System.out.println("Socket chiuso");

				} else if (Controller.client != null) {
					Controller.client.socketClose();
					System.out.println("Socket chiuso");

				}

				Platform.exit();
				System.exit(0);
			});
		});

		host.setOnAction(e -> {
			if (connect.isSelected()) {
				host.setSelected(false);
				new BasicAlertBox("Error", "You are connected, cannot host", 250, 100);
			} else {
				if (isHosting) {
					server.socketClose();
					server = null;
					isHosting = false;
					System.out.println("DisconnectedHosting");
				} else {
					new PortBox("Host", 250, 250);
					if (!isHosting) {
						host.setSelected(false);
					}

				}

			}

		});
		connect.setOnAction(e -> {
			if (host.isSelected()) {
				connect.setSelected(false);
				new BasicAlertBox("Error", "You are hosting, cannot connect", 250, 100);
			} else {
				if (isConnected) {
					if (client.socket != null) {
						client.socketClose();
					}

					client = null;
					isConnected = false;
					System.out.println("DisconnectedTheClient");
				} else {
					new ConnectionBox("Connect", 250, 250);
					if (!isConnected) {
						connect.setSelected(false);
					}

				}

			}
		});

		about.setOnAction(e -> {
			new BasicAlertBox("About", "Created By Sawii00 and Felucco", 200, 200);
		});

	}

}
