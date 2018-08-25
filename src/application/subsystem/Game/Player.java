package application.subsystem.Game;

import application.subsystem.Utils.Color;

public class Player {
	
	Color color;
	public int points = 0;
	public String name;
	
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;	
	}
	
}
