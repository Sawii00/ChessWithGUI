package application.subsystem.Pieces;

import java.util.ArrayList;

import application.Controller;
import application.subsystem.Game.Board;
import application.subsystem.Game.Player;
import application.subsystem.Game.PlayerManager;
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

	public boolean calcDestination(Square destination) {

		if (isValidPath(destination)) {

			for (Square i : path(destination)) {
				if (this.position.isEqual(i.position)) {

					continue;

				} else {

					if (i.piece != null) {

						if (this.player == i.piece.player) {

//							System.out.print("Incontrato pezzo alleato su ");
//							System.out.print(i.position.id_x);
//							System.out.print(", ");
//							System.out.println(i.position.id_y);

							return false;

						} else {

//							System.out.print("Incontrato pezzo nemico su ");
//							System.out.print(i.position.id_x);
//							System.out.print(", ");
//							System.out.println(i.position.id_y);

							if (i == destination) {
//								System.out.println("Destinazione-->Pezzo mangiato");
								return true;
							} else {

//								System.out.println("Non destinazione, mossa non valida");
								return false;

							}

						}

					} else {
						continue;

					}

				}
			}

			return true;

		} else {

			return false;

		}

	}

	public void move(Square destination) {
		if (calcDestination(destination)) {
			Controller.pm.successfulMove();
			
			for (Square i : path(destination)) {
//				System.out.print(i.position.id_x);
//				System.out.print(", ");
//				System.out.println(i.position.id_y);
				Board.squares[position.id_x][position.id_y].piece = null;
				Board.squares[destination.position.id_x][destination.position.id_y].piece = this;
				position.syncPos(destination);
//				System.out.println("mosso");
				if (destination.piece != null) {
					this.eat(destination.piece);
				}

			}

			if (Controller.client != null) {
				// client method
				String message = (position.id_x) + "," + (Board.height - position.id_y) + "-" + (destination.position.id_x) + ","
						+ (Board.height - destination.position.id_y);
				Controller.client.pieceMoved(message);

			}
			
			if (Controller.server != null) {
				// server method
				String message = position.id_x + "," + position.id_y + "-" + destination.position.id_x + ","
						+ destination.position.id_y;
				Controller.server.pieceMoved(message);
			}

		} else {
//			System.out.println("Rotto");
		}
	}

	public void eat(BasicPiece piece) {
		piece.isEaten = true;
	}

	public abstract Type getType();

}
