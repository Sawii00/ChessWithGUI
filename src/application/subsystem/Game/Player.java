package application.subsystem.Game;

import application.subsystem.Pieces.BasicPiece;
import application.subsystem.Utils.Color;

public class Player {
	
	Color color;
	public int points = 0;
	public String name;
	boolean myTurn = false;
	
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;	
	}
	
	
	public void move(BasicPiece piece, Square destination) {
		if (myTurn) {
			piece.move(destination);
			PlayerManager.successfulMove();			
			//probably we have to set this flag in the if statement of the actual movement of the basicpiece (we want the move to occur)
		}
		
		
	}
	
}
