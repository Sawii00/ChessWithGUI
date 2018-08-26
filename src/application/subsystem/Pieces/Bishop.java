package application.subsystem.Pieces;

import java.util.ArrayList;

import application.subsystem.Game.Player;
import application.subsystem.Game.Square;
import application.subsystem.Game.Board;
import application.subsystem.Utils.Position;
import application.subsystem.Utils.Type;

public class Bishop extends BasicPiece {

	Type type;

	public Bishop(Position position, Player player) {
		super(position, player);
		type = Type.BISHOP;
	}

	// the bishop can only move on the diagonal, therefore has to have the
	// difference in height and width equal

	@Override
	public boolean isValidPath(Square destination) {
		if (Math.abs(position.id_x - destination.position.id_x) == Math.abs(position.id_y - destination.position.id_y)
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

	@Override
	public ArrayList<Square> path(Square destination) {
		ArrayList<Square> path = new ArrayList<Square>();
		int temp = 0;

		if (position.id_x < destination.position.id_x) {
			for (int i = position.id_x; i <= destination.position.id_x; i++) {
				if (position.id_y < destination.position.id_y) {
					path.add(Board.squares[position.id_x + temp][position.id_y + temp]);

				} else {
					path.add(Board.squares[position.id_x + temp][position.id_y - temp]);

				}
				temp++;

			}

		} else {
			for (int i = position.id_x; i >= destination.position.id_x; i--) {

				if (position.id_y < destination.position.id_y) {
					path.add(Board.squares[position.id_x - temp][position.id_y + temp]);

				} else {

					path.add(Board.squares[position.id_x - temp][position.id_y - temp]);

				}
				temp++;

			}

		}

		return path;
	}

}
