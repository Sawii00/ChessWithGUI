package application.subsystem.Pieces;

import application.subsystem.Game.Player;
import application.subsystem.Game.Square;
import application.subsystem.Utils.Position;
import application.subsystem.Utils.Type;

public class Queen extends BasicPiece {

	Type type;

	public Queen(Position position, Player player) {
		super(position, player);
		type = Type.QUEEN;
	}

	// She either moves back and forth and therefore has the same x or y or she
	// moves on a diagonal--> the difference in height and width has to be the same
	@Override
	public boolean isValidPath(Square destination) {

		if ((destination.position.id_x == position.id_x || destination.position.id_y == position.id_y
				|| Math.abs(position.id_x - destination.position.id_x) == Math.abs(position.id_y - destination.position.id_y) ) && !destination.position.isEqual(position)) {
			return true;
		} else {
			return false;
		}

	}

	

	@Override
	public Type getType() {
		return type;
	}

}
