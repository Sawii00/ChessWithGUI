package application.subsystem.Game;

import application.subsystem.Utils.Color;

public class PlayerManager {

	public static Player player1;
	public static Player player2;

	public PlayerManager() {
		player1 = new Player("Player 1", Color.RED);
		player2 = new Player("Player 2", Color.BLUE);

		if (Math.random() < 0.5d) {
			player1.myTurn = true;
		} else {
			player2.myTurn = true;
		}

	}

	public static void successfulMove() {
		if (player1.myTurn) {
			player1.myTurn = false;
			player2.myTurn = true;
		} else {
			player1.myTurn = true;
			player2.myTurn = false;
		}

	}

}