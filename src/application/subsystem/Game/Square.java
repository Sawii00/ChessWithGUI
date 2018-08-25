package application.subsystem.Game;

import application.subsystem.Pieces.BasicPiece;
import application.subsystem.Utils.Position;

public class Square {
	
	public Position position;
	public BasicPiece piece;
	

	
	
	public Square(Position position, BasicPiece piece) {
		this.position = position;
		this.piece = piece;
		
	}

}
