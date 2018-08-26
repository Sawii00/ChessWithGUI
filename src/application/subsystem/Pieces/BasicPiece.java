package application.subsystem.Pieces;
import java.util.ArrayList;

import application.subsystem.Game.Board;
import application.subsystem.Game.Player;
import application.subsystem.Game.Square;
import application.subsystem.Utils.Position;
import application.subsystem.Utils.Type;

public abstract class BasicPiece {
	
	public Position position;
	public boolean isEaten = false;
	public static int points;
	public Player player;
	
	public BasicPiece(Position position, Player player) {
		this.position = position;
		this.player = player;	
		
	}
	
	
	
	
	public abstract boolean isValidPath(Square destination);
	
	public abstract ArrayList<Square> path(Square destination);
	
	public void move(Square destination){
		if(isValidPath(destination)) {
			for (Square i : path(destination)) {
				System.out.print(i.position.id_x);
				System.out.print(", ");
				System.out.println(i.position.id_y);
			Board.squares[position.id_x][position.id_y].piece = null;
			Board.squares[destination.position.id_x][destination.position.id_y].piece = this;
			position.syncPos(destination);
			System.out.println("mosso");
			if (destination.piece!=null) {
				this.eat(destination.piece);
				
				
			}
			
				
				
			}
		}else {
			System.out.println("Rotto");
		}
	}
	
	public void eat(BasicPiece piece) {
		piece.isEaten = true;	
	}
	
	public abstract Type getType();
	

}
