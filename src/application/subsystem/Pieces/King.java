package application.subsystem.Pieces;

import application.subsystem.Game.Player;
import application.subsystem.Game.Square;
import application.subsystem.Utils.Position;
import application.subsystem.Utils.Type;

public class King extends BasicPiece {

	Type type;

	public King(Position position, Player player) {
		super(position, player);
		type = Type.KING;
	}

	//THE KING CAN MOVE WHEREVER HE WANTS BUT FOR ONLY 1 PLACE AT A TIME. TO CHECK IF HE WANTS TO MOVE 1 PLACE 
	//JUST CHECK THAT HE DOES NOT MOVE MORE THAN 1 PLACE ON THE X AND Y AXIS
	
	@Override
	public boolean isValidPath(Square destination) {
		if (Math.abs(position.id_x-destination.position.id_x)<=1 && Math.abs(position.id_y-destination.position.id_y)<=1 && !destination.position.isEqual(position)) {
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
