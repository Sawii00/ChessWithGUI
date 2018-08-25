package application.subsystem.Utils;

public class Position {
	
	public int id_x;
	public int id_y;
	
	public Position(int id_x,int id_y) {
		this.id_x = id_x;
		this.id_y = id_y;
		
	}
	
	public boolean isEqual(Position position) {
		if(id_x == position.id_x && id_y == position.id_y)
			return true;
		else
			return false;
	}
	public String toString() {
		return "X: "+id_x+ " Y: "+id_y;
	}
	
	

}
