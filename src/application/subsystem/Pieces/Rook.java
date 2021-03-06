package application.subsystem.Pieces;

import java.util.ArrayList;

import application.subsystem.Game.Board;
import application.subsystem.Game.Player;
import application.subsystem.Game.Square;
import application.subsystem.Utils.Position;
import application.subsystem.Utils.Type;

public class Rook extends BasicPiece {

	Type type;

	public Rook(Position position, Player player) {
		super(position, player);
		type = Type.ROOK;
	}

	// the rook can only back and forth and left and right--> it always has either
	// the same x or the same y when it moves to another place-->if it does not have
	// that, then it is not moving on the + and therefore is not valid
	@Override
	public boolean isValidPath(Square destination) {
		if (destination.position.id_x != position.id_x && destination.position.id_y != position.id_y || destination.position.isEqual(position)) {
			return false;
		} else {
			return true;
		}
	}

	

	@Override
	public Type getType() {
		return type;
	}
	
	@Override
	public ArrayList<Square> path(Square destination) {
		ArrayList<Square> path = new ArrayList<Square>();

		if (destination.position.id_x == position.id_x) {
			if (destination.position.id_y < position.id_y) {
				for (int i = position.id_y; i >= destination.position.id_y; i--) {
					path.add(Board.squares[position.id_x][i]);

				}
			} else {
				for (int i = position.id_y; i <= destination.position.id_y; i++) {
					path.add(Board.squares[position.id_x][i]);

				}

			}

		} else if (destination.position.id_y == position.id_y) {
			if (destination.position.id_x < position.id_x) {
				for (int i = position.id_x; i >= destination.position.id_x; i--) {
					path.add(Board.squares[i][position.id_y]);

				}
			} else {
				for (int i = position.id_x; i <= destination.position.id_x; i++) {
					path.add(Board.squares[i][position.id_y]);

				}

			}
		}

		return path;

	}

}
