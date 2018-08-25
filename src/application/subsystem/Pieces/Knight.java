package application.subsystem.Pieces;

import application.subsystem.Game.Player;
import application.subsystem.Game.Square;
import application.subsystem.Utils.Position;
import application.subsystem.Utils.Type;

public class Knight extends BasicPiece {
	Type type;

	public Knight(Position position, Player player) {
		super(position, player);
		type = Type.KNIGHT;
	}


	@Override
	public boolean isValidPath(Square destination) {
		if (Math.abs(position.id_x - destination.position.id_x)
				+ Math.abs(position.id_y - destination.position.id_y) == 3
				&& Math.abs(position.id_x - destination.position.id_x) != 0
				&& Math.abs(position.id_y - destination.position.id_y) != 0
				&& !destination.position.isEqual(position)) {
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
