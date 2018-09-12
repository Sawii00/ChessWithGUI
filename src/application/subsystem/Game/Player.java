package application.subsystem.Game;

import application.subsystem.Pieces.BasicPiece;
import application.subsystem.Utils.Color;

public class Player {
	
	Color color;
	public int points = 0;
	public String name;
	public boolean myTurn = false;
	public boolean virtual = false;
	
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;	
	}
	
	
	public void move(BasicPiece piece, Square destination) {
		if (myTurn&&!virtual) {
			piece.move(destination);
		}
		
		
		
		
	}
	
}
