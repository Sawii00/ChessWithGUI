package application.subsystem.Game;

import application.Controller;
import application.subsystem.Pieces.Bishop;
import application.subsystem.Pieces.King;
import application.subsystem.Pieces.Knight;
import application.subsystem.Pieces.Pawn;
import application.subsystem.Pieces.Queen;
import application.subsystem.Pieces.Rook;
import application.subsystem.Utils.Color;
import application.subsystem.Utils.Position;

public class Board {

	static public Square[][] squares;
	int width, height = 8;

	public Board(int width, int height) {

		this.width = width;
		this.height = height;
		squares = new Square[width][height];

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {

				// setting up the board with null pieces
				squares[i][j] = new Square(new Position(i, j), null);

			}
		}

		setUpPieces();
	}

	static public void reverse()
	{
		System.out.println("Reversing");
		for(int i = 0; i < squares.length; ++i)
		{
			for(int j = 0; j < squares[i].length; ++j)
			{
				Square sq = squares[i][j];
				squares[i][j] = squares[squares.length - 1 - i][j];
				squares[squares.length - 1 - i][j] = sq;
			}
			
		}
				
	}
	
	public static String boardToString() {
		String result = "";
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				try {
					if (squares[i][j].piece.player == Controller.pm.player1) {
						result = result + "1," + squares[i][j].piece.getType();
					} else if (squares[i][j].piece.player == Controller.pm.player2) {
						result = result + "2," + squares[i][j].piece.getType();
					}
				} catch (NullPointerException e) {
					//it should not matter whose NULL it is... 1 or 2 is indifferent
						result = result + "1,NULL";
				}

				result = result + ";";
			}

			
		}
		
		if (Controller.pm.player1.myTurn) {
			result = result + "1";
		} else if (Controller.pm.player2.myTurn) {
			result = result + "2";

		} else {
			System.out.println("Error with turns");
		}
		return result;
	}

	public void setUpPieces() {
		// set the initial pieces on the board
		width = width - 1;
		height = height - 1;
		// PLAYER 1
		// PAWNS
		for (int i = 0; i < squares.length; i++) {
			squares[i][1].piece = new Pawn(new Position(i, 1), Controller.pm.player1);
		}
		// ROOKS
		squares[0][0].piece = new Rook(new Position(0, 0), Controller.pm.player1);
		squares[width][0].piece = new Rook(new Position(width, 0), Controller.pm.player1);
		// KNIGHTS
		squares[1][0].piece = new Knight(new Position(1, 0), Controller.pm.player1);
		squares[width - 1][0].piece = new Knight(new Position(width - 1, 0), Controller.pm.player1);
		// BISHOPS
		squares[2][0].piece = new Bishop(new Position(2, 0), Controller.pm.player1);
		squares[width - 2][0].piece = new Bishop(new Position(width - 2, 0), Controller.pm.player1);
		// QUEEN
		squares[3][0].piece = new Queen(new Position(3, 0), Controller.pm.player1);
		// KING
		squares[width - 3][0].piece = new King(new Position(width - 3, 0), Controller.pm.player1);
		// PLAYER 2
		// PAWNS
		for (int i = 0; i < squares.length; i++) {
			squares[i][height - 1].piece = new Pawn(new Position(i, height - 1), Controller.pm.player2);
		}
		// ROOKS
		squares[0][height].piece = new Rook(new Position(0, height), Controller.pm.player2);
		squares[width][height].piece = new Rook(new Position(width, height), Controller.pm.player2);
		// KNIGHTS
		squares[1][height].piece = new Knight(new Position(1, height), Controller.pm.player2);
		squares[width - 1][height].piece = new Knight(new Position(width - 1, height), Controller.pm.player2);
		// BISHOPS
		squares[2][height].piece = new Bishop(new Position(2, height), Controller.pm.player2);
		squares[width - 2][height].piece = new Bishop(new Position(width - 2, height), Controller.pm.player2);
		// QUEEN
		squares[3][height].piece = new Queen(new Position(3, height), Controller.pm.player2);
		// KING
		squares[width - 3][height].piece = new King(new Position(width - 3, height), Controller.pm.player2);
	}
	
	public void setUpPiecesReversed() {
		// set the initial pieces on the board
		width = width - 1;
		height = height - 1;
		// PLAYER 1
		// PAWNS
		for (int i = 0; i < squares.length; i++) {
			squares[i][1].piece = new Pawn(new Position(i, 1), Controller.pm.player2);
		}
		// ROOKS
		squares[0][0].piece = new Rook(new Position(0, 0), Controller.pm.player2);
		squares[width][0].piece = new Rook(new Position(width, 0), Controller.pm.player2);
		// KNIGHTS
		squares[1][0].piece = new Knight(new Position(1, 0), Controller.pm.player2);
		squares[width - 1][0].piece = new Knight(new Position(width - 1, 0), Controller.pm.player2);
		// BISHOPS
		squares[2][0].piece = new Bishop(new Position(2, 0), Controller.pm.player2);
		squares[width - 2][0].piece = new Bishop(new Position(width - 2, 0), Controller.pm.player2);
		// QUEEN
		squares[3][0].piece = new Queen(new Position(3, 0), Controller.pm.player2);
		// KING
		squares[width - 3][0].piece = new King(new Position(width - 3, 0), Controller.pm.player2);
		// PLAYER 2
		// PAWNS
		for (int i = 0; i < squares.length; i++) {
			squares[i][height - 1].piece = new Pawn(new Position(i, height - 1), Controller.pm.player1);
		}
		// ROOKS
		squares[0][height].piece = new Rook(new Position(0, height), Controller.pm.player1);
		squares[width][height].piece = new Rook(new Position(width, height), Controller.pm.player1);
		// KNIGHTS
		squares[1][height].piece = new Knight(new Position(1, height), Controller.pm.player1);
		squares[width - 1][height].piece = new Knight(new Position(width - 1, height), Controller.pm.player1);
		// BISHOPS
		squares[2][height].piece = new Bishop(new Position(2, height), Controller.pm.player1);
		squares[width - 2][height].piece = new Bishop(new Position(width - 2, height), Controller.pm.player1);
		// QUEEN
		squares[3][height].piece = new Queen(new Position(3, height), Controller.pm.player1);
		// KING
		squares[width - 3][height].piece = new King(new Position(width - 3, height), Controller.pm.player1);
	}

}
