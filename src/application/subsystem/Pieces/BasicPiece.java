package application.subsystem.Pieces;
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
	
	public void move(Square destination){
		if(isValidPath(destination)) {
			Board.squares[position.id_x][position.id_y].piece = null;
			Board.squares[destination.position.id_x][destination.position.id_y].piece = this;
		}else {
			System.out.println("Rotto");
		}
	}
	
	public void eat(BasicPiece piece) {
		piece.isEaten = true;	
	}
	
	public abstract Type getType();
	

}
