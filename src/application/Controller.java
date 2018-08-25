package application;

import application.subsystem.Game.Board;
import application.subsystem.Pieces.BasicPiece;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

public class Controller {

	static Board board;
	static GridPane gridPane;
	static Label tempLabel;

	public Controller() {

	}

	public static void initialize() {
		board = new Board(8, 8);

		gridPane = (GridPane) Main.mainScene.lookup("#gridPane");

		syncArrayGrid();

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

		int targetRowID = GridPane.getRowIndex((Label) e.getSource());
		int targetColID = GridPane.getColumnIndex((Label) e.getSource());
		// CORRECTLY RETRIEVED THE R0W AND COL ID OF THE GRID LETS GOOOOOOOOOOO

		//retrieve the moving piece
		BasicPiece moving = Board.squares[sourceColID][sourceRowID].piece;
		moving.move(Board.squares[targetColID][targetRowID]);
		
		
		e.setDropCompleted(true);
		e.consume();
	}
	
	public static void syncArrayGrid() {
		
		for (int i = 0; i < Board.squares.length; i++) {
			for (int j = 0; j < Board.squares[i].length; j++) {
				tempLabel = (Label) gridPane.lookup("#pos" + i + "" + j + "");

				try {
					tempLabel.setText(Board.squares[i][j].piece.getType().toString());
				} catch (Exception e) {
					tempLabel.setText("");
				}
			}
		}
	}

}
