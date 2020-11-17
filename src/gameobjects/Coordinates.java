package gameobjects;

import java.lang.Math; 

public class Coordinates {

	private double x;
	private double y;

	public Coordinates() {
		this(0.0, 0.0);
	}

	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double distance(double x, double y) {
		double distance = Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2));

		return distance;
	}

}
