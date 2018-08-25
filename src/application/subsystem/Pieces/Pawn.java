package application.subsystem.Pieces;

import application.subsystem.Game.Player;
import application.subsystem.Game.Square;
import application.subsystem.Utils.Position;
import application.subsystem.Utils.Type;

public class Pawn extends BasicPiece {

	Type type;
	
	public boolean isFirstMove = true;

	public Pawn(Position position, Player player) {
		super(position, player);
		type = Type.PAWN;
	}
	// The pawns can only move forward, therefore their destination has to have the
	// same x_id but different x_id
	// it has to move at least 1 but up to 2 places

	@Override
	public boolean isValidPath(Square destination) {

		// NEED TO IMPLEMENT THE CAPABILITY TO MOVE DIAGONALLYFORWARD TO EAT AN ENEMY
		// PIECE

		if (isFirstMove && position.id_x == destination.position.id_x && position.id_y != destination.position.id_y
				&& position.id_y - destination.position.id_y <= 2) {
			isFirstMove = false;
			return true;
		} else if(destination.piece != null && destination.position.id_x-position.id_x == 1 && destination.position.id_y-position.id_y == 1) {
			isFirstMove = false;
			return true;
		}else {
			if (position.id_x == destination.position.id_x && destination.position.id_y - position.id_y == 1) {
				isFirstMove = false;
				return true;
			} else {
				return false;
			}
		}
	}

	

	@Override
	public Type getType() {
		return type;
	}

}
