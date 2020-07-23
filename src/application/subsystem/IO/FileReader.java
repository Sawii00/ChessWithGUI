package application.subsystem.IO;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import application.Controller;
import application.subsystem.Game.Board;
import application.subsystem.Game.Player;
import application.subsystem.Pieces.*;
import application.subsystem.Utils.Position;

public class FileReader {
	String content;

	public FileReader(String uri) throws IOException {

		FileInputStream fis = null;
		DataInputStream reader = null;

		try {
			fis = new FileInputStream(uri);
			reader = new DataInputStream(fis);
			content = reader.readUTF();
			applyFileContent(content);

		} finally {
			// releases any associated system files with this stream
			if (fis != null)
				fis.close();
			if (reader != null)
				reader.close();
		}
	}

	public static void applyFileContent(String file) {

		String temp[] = file.split(";");
		String temp2[];
		Player player;
		Position position;
		BasicPiece piece = null;

		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				position = new Position(i, k);
				temp2 = temp[k + 8 * i].split(",");
				if (temp2[0].equals("1")) {
					player = Controller.pm.player1;
				} else {
					player = Controller.pm.player2;
				}

				if (temp2[1].equals("BISHOP")) {
					piece = new Bishop(position, player);
				} else if (temp2[1].equals("KING")) {
					piece = new King(position, player);
				} else if (temp2[1].equals("KNIGHT")) {
					piece = new Knight(position, player);
				} else if (temp2[1].equals("PAWN")) {
					piece = new Pawn(position, player);
				} else if (temp2[1].equals("QUEEN")) {
					piece = new Queen(position, player);
				} else if (temp2[1].equals("ROOK")) {
					piece = new Rook(position, player);
				} else if (temp2[1].equals("NULL")) {
					piece = null;
				}
				
				if (temp[temp.length-1].equals("2")){
					Controller.pm.player2.myTurn=true;
					Controller.pm.player1.myTurn=false;	
				} else if (temp[temp.length-1].equals("1")){
					Controller.pm.player2.myTurn=false;
					Controller.pm.player1.myTurn=true;
				}else {
					
					System.out.println("Error: no turn info");
					
				}
				
				Board.squares[i][k].piece=piece;

			}

		}
		
		//Controller.syncArrayGrid();

	}

}
