package com.rover;

public class Rover {
	private int xCordinate;	
	private int yCordinate;
	private Character orientation;
	
	public Rover(int xCordinate, int yCordinate, Character orientation) {
		super();
		this.xCordinate = xCordinate;
		this.yCordinate = yCordinate;
		this.orientation = orientation;
	}

	public int getxCordinate() {
		return xCordinate;
	}

	public void setxCordinate(int xCordinate) {
		this.xCordinate = xCordinate;
	}

	public int getyCordinate() {
		return yCordinate;
	}

	public void setyCordinate(int yCordinate) {
		this.yCordinate = yCordinate;
	}

	public Character getOrientation() {
		return orientation;
	}

	public void setOrientation(Character orientation) {
		this.orientation = orientation;
	}

	@Override
	public String toString() {
		return "Rover [xCordinate=" + xCordinate + ", yCordinate=" + yCordinate + ", orientation=" + orientation + "]";
	}
	
	
}
