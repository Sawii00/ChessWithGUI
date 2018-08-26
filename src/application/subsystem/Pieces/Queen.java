package application.subsystem.Pieces;

import java.util.ArrayList;

import application.subsystem.Game.Board;
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

		if ((destination.position.id_x == position.id_x || destination.position.id_y == position.id_y || Math
				.abs(position.id_x - destination.position.id_x) == Math.abs(position.id_y - destination.position.id_y))
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

	// Scary code down there, be aware
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

		} else {
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

		}

		return path;

	}

}
